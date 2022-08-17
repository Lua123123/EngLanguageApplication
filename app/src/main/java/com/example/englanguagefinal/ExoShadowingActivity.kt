package com.example.englanguagefinal

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.ActivityInfo
import android.content.res.Resources
import android.graphics.drawable.ColorDrawable
import android.media.AudioManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.SparseArray
import android.view.*
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.GestureDetectorCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import at.huber.youtubeExtractor.VideoMeta
import at.huber.youtubeExtractor.YouTubeExtractor
import at.huber.youtubeExtractor.YtFile
import com.example.englanguagefinal.adapter.ExoShadowingAdapter
import com.example.englanguagefinal.callbackadapter.ExoShadowingCallBack
import com.example.englanguagefinal.databinding.ActivityExoShadowingBinding
import com.example.englanguagefinal.databinding.MoreFeaturesBinding
import com.example.englanguagefinal.databinding.SpeedDialogBinding
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.example.englanguagefinal.model.exo.ShadowingList
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.github.vkay94.dtpv.youtube.YouTubeOverlay
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MergingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.DecimalFormat
import kotlin.math.abs


@SuppressLint("StaticFieldLeak")
class ExoShadowingActivity : AppCompatActivity(), GestureDetector.OnGestureListener {
    private lateinit var binding: ActivityExoShadowingBinding
    private lateinit var viewModel: ViewModelApp

    companion object {
        private lateinit var gestureDetectorCompat: GestureDetectorCompat
        private lateinit var exoShadowingAdapter: ExoShadowingAdapter
        private var videoList: ArrayList<ShadowingList> = ArrayList()
        private lateinit var trackSelector: DefaultTrackSelector
        private lateinit var btnFullVideo: ImageView
        private lateinit var runnable: Runnable
        private lateinit var myUrl: String
        private lateinit var myName: String
        private var isFullScreen: Boolean = false
        private var isSubtitle: Boolean = false
        private var isLocked: Boolean = false
        private var playWhenReady = true
        private var audioManager: AudioManager? = null
        private var exoPlayer: ExoPlayer? = null
        private var maxVolume: Int? = null
        private var speed: Float = 1.0f
        private var brightNess: Int = 0
        private var volume: Int = 0
        private var currentWindow = 0
        private var playbackPosition = 0L
    }

    @SuppressLint("ClickableViewAccessibility", "SourceLockedOrientationActivity", "ResourceAsColor", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoShadowingBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]
        val bundle = intent.extras
        myUrl = bundle?.getString("url").toString()
        myName = bundle?.getString("name").toString()

        initPlayer()
        playYoutube(myUrl)
        setRecyclerview()
        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager

        gestureDetectorCompat =
            GestureDetectorCompat(this@ExoShadowingActivity, this@ExoShadowingActivity)

        binding.playerView.setOnTouchListener { _, motionEvent ->
            gestureDetectorCompat.onTouchEvent(motionEvent)
            if (motionEvent.action == MotionEvent.ACTION_UP) {
                binding.brightIcon.visibility = View.GONE
                binding.volumeIcon.visibility = View.GONE
            }
            return@setOnTouchListener false
        }

        binding.btnLock.setOnClickListener {
            if (!isLocked) {
                //for hiding
                isLocked = true
                binding.playerView.hideController()
                binding.playerView.useController = false
                binding.btnLock.setImageResource(R.drawable.ic_lock)
            } else {
                //for showing
                isLocked = false
                binding.playerView.useController = true
                binding.playerView.showController()
                binding.btnLock.setImageResource(R.drawable.ic_lock_exit)
            }
        }

        binding.btnBack.setOnClickListener {
            launchActivity(ExoShadowingMenuActivity::class.java)
            finish()
            exoPlayer?.playWhenReady = false
            exoPlayer?.stop()
            exoPlayer?.seekTo(0)
        }

        btnFullVideo.setOnClickListener {
            requestedOrientation = if (!isFullScreen) {
                btnFullVideo.setImageResource(R.drawable.ic_fullscreen_exit)
                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
            } else {
                btnFullVideo.setImageResource(R.drawable.ic_fullscreen)
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
            }
            isFullScreen = !isFullScreen
        }

        binding.btnMoreFeatures.setOnClickListener {
            val customDialog = LayoutInflater.from(this).inflate(R.layout.more_features, null)
            val bindingMF = MoreFeaturesBinding.bind(customDialog)
            val dialog = MaterialAlertDialogBuilder(this).setView(customDialog)
                .setOnCancelListener {}
                .setBackground(ColorDrawable(R.color.nearBlack))
                .create()
            dialog.show()

            bindingMF.btnSpeed.setOnClickListener {
                dialog.dismiss()
                val customDialogS = LayoutInflater.from(this).inflate(R.layout.speed_dialog, null)
                val bindingS = SpeedDialogBinding.bind(customDialogS)
                val dialogS = MaterialAlertDialogBuilder(this).setView(customDialogS).setCancelable(true).setBackground(ColorDrawable(R.color.nearBlack)).create()
                dialogS.show()
                bindingS.textSpeed.text = "${DecimalFormat("#.##").format(speed)} X"
                bindingS.btnMinus.setOnClickListener {
                    changeSpeed(isIncrement = false)
                    bindingS.textSpeed.text = "${DecimalFormat("#.##").format(speed)} X"
                }
                bindingS.btnPlus.setOnClickListener {
                    changeSpeed(isIncrement = true)
                    bindingS.textSpeed.text = "${DecimalFormat("#.##").format(speed)} X"
                }
            }
            bindingMF.btnSubtitle.setOnClickListener {
                if (isSubtitle) {
                    trackSelector.parameters = DefaultTrackSelector.Parameters.Builder(this).setRendererDisabled(C.TRACK_TYPE_VIDEO, true).build()
                    toast("Subtitles Off")
                    isSubtitle = false
                } else {
                    trackSelector.parameters = DefaultTrackSelector.Parameters.Builder(this).setRendererDisabled(C.TRACK_TYPE_VIDEO, false).build()
                    toast("Subtitles On")
                    isSubtitle = true
                }
                dialog.dismiss()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun initPlayer() {
        trackSelector = DefaultTrackSelector(this)
        btnFullVideo = findViewById(R.id.exo_fullscreen)
        speed = 1.0f
        binding.tvName.text = myName
        binding.titleTextView.text = myName
        binding.videoTitle.text =
            "I woke up late yesterday for my job interview. I was not allowed to enter the room for my job interview when I arrived. Then, I forgot to complete my project for my other job and my boss fired me."
        binding.videoTitle.isSelected = true
        exoPlayer = ExoPlayer.Builder(this@ExoShadowingActivity).setTrackSelector(trackSelector).build()
        doubleTabEnable()
        setVisibility()
    }

    @SuppressLint("StaticFieldLeak")
    @Suppress("SameParameterValue")
    private fun playYoutube(url: String) {
        object : YouTubeExtractor(this) {
            override fun onExtractionComplete(ytFiles: SparseArray<YtFile>?, videoMeta: VideoMeta?) {
                if (ytFiles != null) {
                    val iTag = 137
                    val audioTag = 140
                    val videoUrl = ytFiles[iTag].url ?: ""
                    val audioUrl = ytFiles[audioTag].url ?: ""

                    val audioSource: MediaSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(audioUrl))

                    val videoSource: MediaSource =
                        ProgressiveMediaSource.Factory(DefaultHttpDataSource.Factory())
                            .createMediaSource(MediaItem.fromUri(videoUrl))
                    exoPlayer?.setMediaSource(
                        MergingMediaSource(true, videoSource, audioSource),
                        true
                    )
                    exoPlayer?.prepare()
                    exoPlayer?.repeatMode = ExoPlayer.REPEAT_MODE_ONE
                    exoPlayer?.playWhenReady = playWhenReady
                    exoPlayer?.seekTo(currentWindow, playbackPosition)
                }
            }
        }.extract(url)
    }

    private fun setVisibility() {
        runnable = Runnable {
            if (binding.playerView.isControllerVisible) changeVisibility(View.VISIBLE)
            else changeVisibility(View.INVISIBLE)
            Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
    }

    private fun changeVisibility(visibility: Int) {
        binding.topController.visibility = visibility
        binding.btnRepeat.visibility = visibility
        binding.btnBack.visibility = visibility
        if (isLocked) binding.btnLock.visibility = View.VISIBLE
        else binding.btnLock.visibility = visibility
    }

    private fun doubleTabEnable() {
        binding.playerView.player = exoPlayer
        binding.youtubeOverlay.performListener(object : YouTubeOverlay.PerformListener {
            override fun onAnimationEnd() {
                binding.youtubeOverlay.visibility = View.GONE
            }

            override fun onAnimationStart() {
                binding.youtubeOverlay.visibility = View.VISIBLE
            }
        })
        binding.youtubeOverlay.player(exoPlayer!!)
    }

    private fun changeSpeed(isIncrement: Boolean) {
        if (isIncrement) {
            if (speed < 2.9f) {
                speed += 0.10f
            }
        } else {
            if (speed > 0.20f) {
                speed -= 0.10f
            }
        }
        exoPlayer?.setPlaybackSpeed(speed)
    }

    override fun onScroll(e1: MotionEvent?, e2: MotionEvent?, distanceX: Float, distanceY: Float): Boolean {
        val sWidth = Resources.getSystem().displayMetrics.widthPixels
        if (abs(distanceX) < abs(distanceY)) {
            if (e1!!.x < sWidth / 2) {
                //brightness
                binding.brightIcon.visibility = View.VISIBLE
                binding.volumeIcon.visibility = View.GONE
                val increase = distanceY > 0
                val newValue = if (increase) brightNess + 1 else brightNess - 1
                if (newValue in 0..100) brightNess = newValue
                binding.brightIcon.text = brightNess.toString()
                setScreenBrightness(brightNess)
            } else {
                //volume
                binding.brightIcon.visibility = View.GONE
                binding.volumeIcon.visibility = View.VISIBLE
                maxVolume = (audioManager!!.getStreamMaxVolume(AudioManager.STREAM_MUSIC))
                val increase = distanceY > 0
                val newValue = if (increase) volume + 1 else volume - 1
                if (newValue in 0..maxVolume!!.toInt()) volume = newValue
                binding.volumeIcon.text = volume.toString()
                audioManager?.setStreamVolume(AudioManager.STREAM_MUSIC, volume, 0)
            }
        }
        return true
    }

    private fun setScreenBrightness(value: Int) {
        val d = 1.0f / 30
        val lp = this.window.attributes
        lp.screenBrightness = d * value
        this.window.attributes = lp
    }

    private fun setRecyclerview() {

        viewModel.valueExoShadowing(videoList)

        binding.recyclerView.apply {
            val itemDecoration = DividerItemDecoration(this@ExoShadowingActivity, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(this@ExoShadowingActivity, R.drawable.divider_rcv)!!)
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(this@ExoShadowingActivity)
        }

        exoShadowingAdapter = ExoShadowingAdapter(this@ExoShadowingActivity, videoList)
        binding.recyclerView.adapter = exoShadowingAdapter

        exoShadowingAdapter.exoListener(object : ExoShadowingCallBack {
            override fun shadowingOnItemClickListener(url: String, name: String) {
                if (myUrl == url) {
                    toast("Video playing.")
                } else {
                    myUrl = url
                    myName = name
                    playYoutube(myUrl)
                    binding.tvName.text = myName
                    binding.titleTextView.text = myName
                }
            }
        })
    }

    override fun onDown(e: MotionEvent?): Boolean = false

    override fun onShowPress(e: MotionEvent?) = Unit

    override fun onSingleTapUp(e: MotionEvent?): Boolean = false

    override fun onLongPress(e: MotionEvent?) = Unit

    override fun onFling(e1: MotionEvent?, e2: MotionEvent?, velocityX: Float, velocityY: Float): Boolean = false

    override fun onStart() {
        super.onStart()
        if (Build.VERSION.SDK_INT > 24) {
            initPlayer()
        }
    }

    override fun onResume() {
        super.onResume()
        if (Build.VERSION.SDK_INT < 24 || exoPlayer == null) {
            initPlayer()
            hideSystemUI()
        }
        if (brightNess != 0) setScreenBrightness(brightNess)
    }

    override fun onPause() {
        if (Build.VERSION.SDK_INT < 24) releasePlayer()
        super.onPause()
    }

    override fun onStop() {
        if (Build.VERSION.SDK_INT < 24) releasePlayer()
        super.onStop()
        exoPlayer?.playWhenReady = false
        exoPlayer?.stop()
        exoPlayer?.seekTo(0)
        ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    private fun releasePlayer() {
        if (exoPlayer != null) {
            playWhenReady = exoPlayer!!.playWhenReady
            exoPlayer?.release()
            exoPlayer = null
        }
    }

    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

}
package com.example.englanguagefinal

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import com.example.englanguagefinal.adapter.ExoAdapter
import com.example.englanguagefinal.callbackadapter.ExoCallBack
import com.example.englanguagefinal.databinding.ActivityExoBinding
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.model.exo.DataExo
import com.example.englanguagefinal.model.exo.MediaObjects
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.ui.PlayerView

class ExoActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoBinding
    private lateinit var exoAdapter: ExoAdapter
    private lateinit var mediaItem: MediaItem
    private lateinit var runnable: Runnable
    private lateinit var word: String
    private var dataExo: DataExo = DataExo()
    private var mediaObjectList: MutableList<MediaObjects> = ArrayList()
    private val mSnapHelper: SnapHelper = PagerSnapHelper()
    private var isLocked: Boolean = false
    private var exoPlayer: ExoPlayer? = null

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_exo)
        binding = DataBindingUtil.setContentView(this@ExoActivity, R.layout.activity_exo)
        getDataBundle()

        binding.recyclerView.apply {
            val linearLayoutManager = LinearLayoutManager(this@ExoActivity)
            linearLayoutManager.orientation = RecyclerView.VERTICAL
            layoutManager = linearLayoutManager
            mSnapHelper.attachToRecyclerView(this)
        }

        if (mediaObjectList.size == 0) dataExo.dataExo(word, this@ExoActivity, mediaObjectList)
        exoAdapter = ExoAdapter(this@ExoActivity, mediaObjectList)
        binding.recyclerView.adapter = exoAdapter
        exoAdapter.notifyDataSetChanged()

        exoAdapter.exoListener(object : ExoCallBack {
            override fun exoCallBack(mp4: String, playerView: PlayerView, videoTitle: TextView, btnBack: ImageButton, btnFullVideo: ImageButton, topController: LinearLayout, bottomController: LinearLayout, layoutCardView: ConstraintLayout, btnLock: ImageButton, textVideo: TextView) {
                videoTitle.isSelected = true

                playerView.addOnAttachStateChangeListener(object :
                    View.OnAttachStateChangeListener {
                    override fun onViewAttachedToWindow(v: View) {
                        createVideo(mp4, playerView)
                        setVisibility(playerView, topController, bottomController, layoutCardView, btnBack, btnLock, textVideo)
                    }

                    override fun onViewDetachedFromWindow(v: View) {
                        playerView.player?.stop()
                    }

                })

                btnBack.setOnClickListener {
                    launchActivity(ExoMenuActivity::class.java)
                    finish()
                }

                btnLock.setOnClickListener {
                    if (!isLocked) {
                        //for hiding
                        isLocked = true
                        playerView.hideController()
                        playerView.useController = false
                        btnLock.setImageResource(R.drawable.ic_lock)
                    } else {
                        //for showing
                        isLocked = false
                        playerView.useController = true
                        playerView.showController()
                        btnLock.setImageResource(R.drawable.ic_lock_exit)
                    }
                }

                btnFullVideo.setOnClickListener {

                }
            }
        })
    }

    private fun createVideo(mp4: String, playerView: PlayerView) {
        exoPlayer = ExoPlayer.Builder(this@ExoActivity).build()
        playerView.player = exoPlayer
        mediaItem = MediaItem.fromUri(mp4)
        exoPlayer?.addMediaItem(mediaItem)
        exoPlayer?.prepare()
        exoPlayer?.repeatMode = ExoPlayer.REPEAT_MODE_ONE
        exoPlayer?.playWhenReady = false
    }

    private fun setVisibility(playerView: PlayerView, topController: LinearLayout, bottomController: LinearLayout, layoutCardView: ConstraintLayout, btnBack: ImageButton, btnLock: ImageButton, textVideo: TextView) {
        runnable = Runnable {
            if (playerView.isControllerVisible) changeVisibility(View.VISIBLE, topController, bottomController, layoutCardView, btnBack, btnLock, textVideo)
            else changeVisibility(View.INVISIBLE, topController, bottomController, layoutCardView, btnBack, btnLock, textVideo)
            Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
        }
        Handler(Looper.getMainLooper()).postDelayed(runnable, 0)
    }

    private fun changeVisibility(
        visibility: Int, topController: LinearLayout, bottomController: LinearLayout, layoutCardView: ConstraintLayout, btnBack: ImageButton, btnLock: ImageButton, textVideo: TextView) {
        topController.visibility = visibility
        bottomController.visibility = visibility
        layoutCardView.visibility = visibility
        btnBack.visibility = visibility
        textVideo.visibility = visibility
        if (isLocked) btnLock.visibility = View.VISIBLE
        else btnLock.visibility = visibility
    }

    private fun getDataBundle() {
        val intent = intent
        val bundle = intent.getBundleExtra("data")
        word = bundle?.getString("word").toString()
    }

    override fun onDestroy() {
        super.onDestroy()
        exoPlayer?.playWhenReady = false
        exoPlayer?.stop()
        exoPlayer?.seekTo(0)
    }

    override fun onPause() {
        super.onPause()
        exoPlayer?.playWhenReady = false
        exoPlayer?.stop()
        exoPlayer?.seekTo(0)
    }
}
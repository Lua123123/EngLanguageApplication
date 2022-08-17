package com.example.englanguagefinal

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.databinding.ActivityOneVocabularyBinding

class OneVocabularyActivity : AppCompatActivity() {
    private lateinit var binding: ActivityOneVocabularyBinding
    private lateinit var mTTS: TextToSpeech
    private lateinit var frontAnim: AnimatorSet
    private lateinit var behindAnim: AnimatorSet
    private lateinit var word: String
    private lateinit var mean: String
    private var isFront = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_one_vocabulary)
        binding = DataBindingUtil.setContentView(this@OneVocabularyActivity, R.layout.activity_one_vocabulary)
        supportActionBar?.hide()
        val bundle = intent.extras
        word = bundle?.getString("word").toString()
        mean = bundle?.getString("mean").toString()
        binding.cartFront.text = word
        binding.cartBehind.text = mean

        mTTS = TextToSpeech(this@OneVocabularyActivity) {}

        flipCart()

        binding.mButtonSpeak.setOnClickListener {
            speak(word)
        }
    }

    private fun flipCart() {
        val scale: Float? = this.resources?.displayMetrics?.density
        binding.cartFront.cameraDistance = 8000 * scale!!
        binding.cartBehind.cameraDistance = 8000 * scale
        frontAnim = AnimatorInflater.loadAnimator(this@OneVocabularyActivity, R.animator.front_animator) as AnimatorSet
        behindAnim = AnimatorInflater.loadAnimator(this@OneVocabularyActivity, R.animator.behind_animation) as AnimatorSet
        binding.flipLayout.setOnClickListener {
            isFront = if (isFront) {
                frontAnim.setTarget(binding.cartFront)
                behindAnim.setTarget(binding.cartBehind)
                frontAnim.start()
                behindAnim.start()
                false
            } else {
                frontAnim.setTarget(binding.cartBehind)
                behindAnim.setTarget(binding.cartFront)
                behindAnim.start()
                frontAnim.start()
                true
            }
        }
    }

    private fun speak(word: String) {
        val text: String = word
        var pitch = binding.seekBarPitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1F
        var speed: Float = binding.seekBarSpeed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1F
        mTTS.setPitch(pitch)
        mTTS.setSpeechRate(speed)
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        mTTS.stop()
        mTTS.shutdown()
        finish()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}
package com.example.englanguagefinal

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.databinding.ActivityTextToSpeechBinding
import java.util.*

class TextToSpeechActivity : AppCompatActivity() {

    private lateinit var binding: ActivityTextToSpeechBinding
    private lateinit var mTTS: TextToSpeech
    private var number: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text_to_speech)
        supportActionBar?.hide()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_text_to_speech)
        binding.radEnglish.isChecked = true
        callMTTS(number)

        binding.radEnglish.setOnClickListener {
            binding.radEnglish.isChecked = true
            binding.radChinese.isChecked = false
            binding.radGermany.isChecked = false
            binding.radFrance.isChecked = false
            number = 1
            callMTTS(number)
        }
        binding.radChinese.setOnClickListener {
            binding.radEnglish.isChecked = false
            binding.radChinese.isChecked = true
            binding.radGermany.isChecked = false
            binding.radFrance.isChecked = false
            number = 2
            callMTTS(number)
        }
        binding.radFrance.setOnClickListener {
            binding.radEnglish.isChecked = false
            binding.radChinese.isChecked = false
            binding.radGermany.isChecked = false
            binding.radFrance.isChecked = true
            number = 3
            callMTTS(number)
        }
        binding.radGermany.setOnClickListener {
            binding.radEnglish.isChecked = false
            binding.radChinese.isChecked = false
            binding.radGermany.isChecked = true
            binding.radFrance.isChecked = false
            number = 4
            callMTTS(number)
        }

        binding.mButtonSpeak.setOnClickListener {
            speak()
        }

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
        }
    }

    private fun speak() {
        val text: String = binding.edtTextToSpeech.text.toString().trim()
        var pitch = binding.seekBarPitch.progress.toFloat() / 50
        if (pitch < 0.1) pitch = 0.1F
        var speed: Float = binding.seekBarSpeed.progress.toFloat() / 50
        if (speed < 0.1) speed = 0.1F

        mTTS.setPitch(pitch) //pitch
        mTTS.setSpeechRate(speed) //speed

        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    private fun callMTTS(number: Int) {
        mTTS = TextToSpeech(this@TextToSpeechActivity) {
            if (number == 1) {
                mTTS.language = Locale.ENGLISH
            }
            if (number == 2) {
                mTTS.language = Locale.CHINESE
            }
            if (number == 3) {
                mTTS.language = Locale.FRANCE
            }
            if (number == 4) {
                mTTS.language = Locale.GERMANY
            }
        }
    }

    override fun onDestroy() {
        mTTS.stop()
        mTTS.shutdown()
        super.onDestroy()
    }

    override fun onBackPressed() {
        super.onBackPressed()
        launchActivity(MainActivity::class.java)
        finish()
    }
}
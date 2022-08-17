package com.example.englanguagefinal.offlinemode

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.navigation.Navigation
import com.example.englanguagefinal.R
import com.example.englanguagefinal.databinding.FragmentTextToSpeechOffBinding

class TextToSpeechOffFragment : Fragment() {
    private lateinit var binding: FragmentTextToSpeechOffBinding
    private lateinit var mTTS: TextToSpeech

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentTextToSpeechOffBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ImageView>(R.id.imgBack).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_textToSpeechFragmentOff_to_mainFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mTTS = TextToSpeech(context) {}
        binding.mButtonSpeak.setOnClickListener {
            speak()
        }
    }

    private fun speak() {
        val text: String = binding.edtTextToSpeech.text.toString().trim()
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
        super.onDestroy()
    }
}
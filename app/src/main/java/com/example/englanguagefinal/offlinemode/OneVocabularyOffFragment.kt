package com.example.englanguagefinal.offlinemode

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.englanguagefinal.R
import com.example.englanguagefinal.databinding.FragmentOneVocabularyOffBinding

class OneVocabularyOffFragment : Fragment() {
    private lateinit var binding: FragmentOneVocabularyOffBinding
    private lateinit var mTTS: TextToSpeech
    private var isFront = true
    private lateinit var frontAnim: AnimatorSet
    private lateinit var behindAnim: AnimatorSet

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentOneVocabularyOffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val word = arguments?.getString("word")
        binding.cartFront.text = word
        binding.cartBehind.text = arguments?.getString("mean")

        flipCart()
        callMTTS()
        binding.mButtonSpeak.setOnClickListener {
            speak(word.toString())
        }
    }

    private fun flipCart() {
        val scale: Float? = this.resources.displayMetrics?.density
        binding.cartFront.cameraDistance = 8000 * scale!!

        binding.cartBehind.cameraDistance = 8000 * scale
        frontAnim = AnimatorInflater.loadAnimator(requireActivity(), R.animator.front_animator) as AnimatorSet
        behindAnim = AnimatorInflater.loadAnimator(requireActivity(), R.animator.behind_animation) as AnimatorSet
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

    private fun callMTTS() {
        mTTS = TextToSpeech(requireActivity()) {}
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
        super.onDestroy()
    }
}
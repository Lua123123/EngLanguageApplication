package com.example.englanguagefinal.fragmentflipcard

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.example.englanguagefinal.R
import com.example.englanguagefinal.databinding.FragmentFlipCard8Binding
import com.example.englanguagefinal.viewmodel.ViewModelApp
import java.util.*

class FlipCardFragment8 : Fragment() {
    private lateinit var frontAnim: AnimatorSet
    private lateinit var behindAnim: AnimatorSet
    private var isFront = true
    private lateinit var binding: FragmentFlipCard8Binding
    private lateinit var mTTS: TextToSpeech
    private val viewModelApp: ViewModelApp by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_flip_card8, container, false)
        binding.lifecycleOwner = this
        val view = binding.root

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        view.findViewById<ConstraintLayout>(R.id.layoutBack).setOnClickListener {
            viewPager?.currentItem = 6
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModelApp.mutableLiveDataClickGetVocabulary()

        flipCart(binding.flipLayout, binding.cartFront, binding.cartBehind)
        flipCart(binding.flipLayout1, binding.cartFront1, binding.cartBehind1)
        flipCart(binding.flipLayout2, binding.cartFront2, binding.cartBehind2)
        flipCart(binding.flipLayout3, binding.cartFront3, binding.cartBehind3)
        flipCart(binding.flipLayout4, binding.cartFront4, binding.cartBehind4)
        flipCart(binding.flipLayout5, binding.cartFront5, binding.cartBehind5)

        callMTTS()

        binding.mButtonSpeak.setOnClickListener {
            val text: String? = viewModelApp.mVocabulary.value?.success?.get(42)?.word
            speak(text!!) }
        binding.mButtonSpeak1.setOnClickListener {
            val text1: String? = viewModelApp.mVocabulary.value?.success?.get(43)?.word
            speak(text1!!) }
        binding.mButtonSpeak2.setOnClickListener {
            val text2: String? = viewModelApp.mVocabulary.value?.success?.get(44)?.word
            speak(text2!!) }
        binding.mButtonSpeak3.setOnClickListener {
            val text3: String? = viewModelApp.mVocabulary.value?.success?.get(45)?.word
            speak(text3!!) }
        binding.mButtonSpeak4.setOnClickListener {
            val text4: String? = viewModelApp.mVocabulary.value?.success?.get(46)?.word
            speak(text4!!) }
        binding.mButtonSpeak5.setOnClickListener {
            val text5: String? = viewModelApp.mVocabulary.value?.success?.get(47)?.word
            speak(text5!!) }

        viewModelApp.mVocabulary.observe(requireActivity())
        {
            binding.cartFront.text = it!!.success?.get(42)?.word
            binding.cartBehind.text = it.success?.get(42)?.mean
            binding.cartFront1.text = it.success?.get(43)?.word
            binding.cartBehind1.text = it.success?.get(43)?.mean
            binding.cartFront2.text = it.success?.get(44)?.word
            binding.cartBehind2.text = it.success?.get(44)?.mean
            binding.cartFront3.text = it.success?.get(45)?.word
            binding.cartBehind3.text = it.success?.get(45)?.mean
            binding.cartFront4.text = it.success?.get(46)?.word
            binding.cartBehind4.text = it.success?.get(46)?.mean
            binding.cartFront5.text = it.success?.get(47)?.word
            binding.cartBehind5.text = it.success?.get(47)?.mean
        }
    }

    private fun callMTTS() {
        mTTS = TextToSpeech(context) { i ->
            if (i == TextToSpeech.SUCCESS) {
                mTTS.language = Locale.ENGLISH
            }
        }
    }

    private fun speak(text: String) {
        mTTS.speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
    }

    override fun onDestroy() {
        mTTS.stop()
        mTTS.shutdown()
        super.onDestroy()
    }

    private fun flipCart(flipLayout: ConstraintLayout, cartFront: TextView, cartBehind: TextView) {
        val scale: Float? = activity?.resources?.displayMetrics?.density
        cartFront.cameraDistance = 8000 * scale!!
        cartBehind.cameraDistance = 8000 * scale

        frontAnim = AnimatorInflater.loadAnimator(activity, R.animator.front_animator) as AnimatorSet
        behindAnim = AnimatorInflater.loadAnimator(activity, R.animator.behind_animation) as AnimatorSet

        flipLayout.setOnClickListener {
            isFront = if (isFront) {
                frontAnim.setTarget(cartFront)
                behindAnim.setTarget(cartBehind)
                frontAnim.start()
                behindAnim.start()
                false
            } else {
                frontAnim.setTarget(cartBehind)
                behindAnim.setTarget(cartFront)
                behindAnim.start()
                frontAnim.start()
                true
            }
        }
    }
}
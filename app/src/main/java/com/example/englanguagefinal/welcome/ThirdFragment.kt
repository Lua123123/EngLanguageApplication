package com.example.englanguagefinal.welcome

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.R
import com.example.englanguagefinal.SignUpActivity
import com.example.englanguagefinal.databinding.FragmentThirdBinding
import com.example.englanguagefinal.extensions.launchActivity

class ThirdFragment : Fragment() {
    private lateinit var binding: FragmentThirdBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_third, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    //HÀM LƯU TRẠ_NG THÁ_I ĐẦU TIÊ_N ĐÃ VÀO APP, LẦN SAU SẼ KHÔ_NG HIỂ_N THỊ NỮA
    private fun onBoardingFinished(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.commit()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.finish.setOnClickListener {
            requireActivity().launchActivity(SignUpActivity::class.java)
            onBoardingFinished()
            requireActivity().finish()
        }
    }

}
package com.example.englanguagefinal.offlinemode

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.ImageView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.example.englanguagefinal.R
import com.example.englanguagefinal.databinding.FragmentLoginOffBinding
import com.example.englanguagefinal.extensions.toast

class LoginFragmentOff : Fragment() {
    private lateinit var binding: FragmentLoginOffBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_off, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ImageView>(R.id.img_wifi).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_loginFragmentOff_to_loginFragmentOn)
        }
        view.findViewById<Button>(R.id.btn_getOffline).setOnClickListener {
            if (binding.checkBoxLogin.isChecked) {
                val bundle = bundleOf(
                    "state" to true
                )
                Navigation.findNavController(view).navigate(R.id.action_loginFragmentOff_to_mainFragment, bundle)
            } else {
                context?.toast("Please agree to the terms of the app!")
            }
        }
        view.findViewById<CheckBox>(R.id.checkBoxLogin).isChecked = true
        return view
    }
}

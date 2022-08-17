package com.example.englanguagefinal.offlinemode

import android.content.Context
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.example.englanguagefinal.R
import com.example.englanguagefinal.ResetPassword
import com.example.englanguagefinal.SignUpActivity
import com.example.englanguagefinal.databinding.FragmentLoginOnBinding
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.google.firebase.auth.FirebaseAuth


class LoginFragmentOn : Fragment() {

    private lateinit var binding: FragmentLoginOnBinding
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var mAuth: FirebaseAuth

    //SHARED PREFERENCE
    private var sharedPref: SharedPreferences? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_login_on, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ImageView>(R.id.img_wifi).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragmentOn_to_loginFragmentOff)
        }
        view.findViewById<TextView>(R.id.tv_dontHaveAnAccount).setOnClickListener {
            Navigation.findNavController(view)
                .navigate(R.id.action_loginFragmentOn_to_signUpActivity)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //SHARED PREFERENCE
        sharedPref = context?.getSharedPreferences("data", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor? = sharedPref?.edit()

        binding.tvForgotPassword.setOnClickListener {
            context?.launchActivity(ResetPassword::class.java)
            requireActivity().finish()
        }

        binding.tvDontHaveAnAccount.setOnClickListener {
            context?.launchActivity(SignUpActivity::class.java)
            requireActivity().finish()
        }

        binding.btnGetLogin.setOnClickListener {
            checkLogin(editor)
        }

        getSharedPreferences()

        mAuth = FirebaseAuth.getInstance()

    }

    private fun checkLogin(editor: SharedPreferences.Editor?) {
        email = binding.edtEmail.text.toString().trim()
        password = binding.edtPassword.text.toString().trim()
        editor?.putString("email", email)
        editor?.putString("password", password)
        editor?.apply()
        val loginViewModelApp = ViewModelApp()

        if (binding.checkBoxLogin.isChecked) {
            if (email.isNotEmpty() && password.isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                if (haveNetwork()) {
                    binding.layoutLoading.visibility = View.VISIBLE
                    loginViewModelApp.mClickLogin(email, password, binding.layoutLoading, mAuth, requireActivity())
                }
                if (!haveNetwork()) {
                    context?.toast("Please connect to internet or switch to offline mode!")
                }
            } else {
                if (email.isEmpty()) {
                    binding.edtEmail.error = "Email is required!"
                    binding.edtEmail.requestFocus()
                    return
                }
                if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    binding.edtEmail.error = "Please provide valid email! @gmail.com?"
                    binding.edtEmail.requestFocus()
                    return
                }
                if (password.isEmpty()) {
                    binding.edtPassword.error = "Password is required!"
                    binding.edtPassword.requestFocus()
                    return
                }
                if (password.length < 6) {
                    binding.edtPassword.error = "Passwords must have at least 6 characters!"
                    binding.edtPassword.requestFocus()
                    return
                }
            }
        } else {
            context?.toast("Please agree to the terms of the app!")
        }

        //SHARED PREFERENCE
        if (!binding.checkRememberMe.isChecked) {
            editor?.remove("email")
            editor?.remove("password")
            editor?.apply()
        }
    }

    private fun getSharedPreferences() {
        binding.checkRememberMe.isChecked = true
        binding.checkBoxLogin.isChecked = true
        //SHARED PREFERENCE
        binding.edtEmail.setText(sharedPref?.getString("email", ""))
        binding.edtPassword.setText(sharedPref?.getString("password", ""))
    }

    /**
     * unless network is available app must get data from API.
     * Otherwise app must get data from local device.
     */
    private fun haveNetwork(): Boolean {
        var haveWIFI = false
        var haveMobileData = false
        val connectivityManager =
            context?.getSystemService(AppCompatActivity.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.allNetworkInfo
        for (info in networkInfo) {
            if (info.typeName.equals("WIFI", ignoreCase = true)) if (info.isConnected) haveWIFI =
                true
            if (info.typeName.equals(
                    "MOBILE",
                    ignoreCase = true
                )
            ) if (info.isConnected) haveMobileData = true
        }
        return haveWIFI || haveMobileData
    }
}
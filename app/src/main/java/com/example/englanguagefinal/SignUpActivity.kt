package com.example.englanguagefinal

import android.os.Bundle
import android.util.Patterns
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.example.englanguagefinal.databinding.ActivitySignUpBinding
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity : AppCompatActivity() {
    private lateinit var mAuth: FirebaseAuth
    private lateinit var name: String
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var conformPassword: String
    private lateinit var binding: ActivitySignUpBinding
    private var viewModelApp: ViewModelApp = ViewModelApp()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_up)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up)
        supportActionBar?.hide()
        mAuth = FirebaseAuth.getInstance()
        binding.checkBoxSignUp.isChecked = true
        binding.tvHaveAnAccount.setOnClickListener {
            launchActivity(LoginActivity::class.java)
            finish()
        }
        binding.btnPostSignUp.setOnClickListener {
            name = binding.edtName.text.toString().trim { it <= ' ' }
            email = binding.edtEmail.text.toString().trim { it <= ' ' }
            password = binding.edtPassword.text.toString().trim { it <= ' ' }
            conformPassword = binding.edtConformPassword.text.toString().trim { it <= ' ' }
            if (binding.checkBoxSignUp.isChecked) {
                if (email.isNotEmpty() && password.isNotEmpty() && name.isNotEmpty() && conformPassword.isNotEmpty() && password.length >= 6 && conformPassword == password && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    viewModelApp.clickSignUp(email, password, name, conformPassword, mAuth, this@SignUpActivity)
                } else {
                    if (name.isEmpty()) {
                        binding.edtName.error = "Name is required!"
                        binding.edtName.requestFocus()
                        return@setOnClickListener
                    }
                    if (email.isEmpty()) {
                        binding.edtEmail.error = "Email is required!"
                        binding.edtEmail.requestFocus()
                        return@setOnClickListener
                    }
                    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                        binding.edtEmail.error = "Please provide valid email! @gmail.com?"
                        binding.edtEmail.requestFocus()
                        return@setOnClickListener
                    }
                    if (password.isEmpty()) {
                        binding.edtPassword.error = "Password is required!"
                        binding.edtPassword.requestFocus()
                        return@setOnClickListener
                    }
                    if (password.length < 6) {
                        binding.edtPassword.error = "Passwords must have at least 6 characters!"
                        binding.edtPassword.requestFocus()
                        return@setOnClickListener
                    }
                    if (conformPassword.isEmpty()) {
                        binding.edtConformPassword.error = "ConformPassword is required!"
                        binding.edtConformPassword.requestFocus()
                        return@setOnClickListener
                    }
                    if (conformPassword != password) {
                        binding.edtConformPassword.error = "Passwords, conform password invalid!"
                        binding.edtConformPassword.requestFocus()
                        return@setOnClickListener
                    }
                }
            } else {
                toast("Please agree to the terms of the app!")
            }
        }
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}
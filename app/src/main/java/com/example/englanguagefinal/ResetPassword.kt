package com.example.englanguagefinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.databinding.ActivityResetPasswordBinding
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.extensions.toast
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.*

class ResetPassword : AppCompatActivity() {
    private lateinit var binding: ActivityResetPasswordBinding
    private lateinit var auth: FirebaseAuth
    private lateinit var reference: DatabaseReference
    private lateinit var user: FirebaseUser
    private lateinit var password: String
    private lateinit var viewModelApp: ViewModelApp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_reset_password)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_reset_password)
        viewModelApp = ViewModelApp()
        auth = FirebaseAuth.getInstance()
        binding.btnResetPassword.setOnClickListener {
            resetPassword()
        }
    }

    private fun resetPassword() {
        val email = binding.edtEmail.text.toString().trim()
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

        binding.progressBar.visibility = View.VISIBLE
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {

                getFirebaseInformation(email)

                toast("CHECK YOU EMAIL TO RESET PASSWORD!")
                launchActivity(LoginActivity::class.java)

            } else toast("TRY AGAIN! SOMETHING WRONG HAPPENED!")
        }
    }

    private fun getFirebaseInformation(email: String) {
        /**
         * FIREBASE
         */
//        user = FirebaseAuth.getInstance().currentUser!!
        reference = FirebaseDatabase.getInstance().getReference("Users")
        reference.child("KIeqwB66jQMgf0NKsAU1RJ48Gez1").child("password")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    password = snapshot.value.toString()
                    Log.d("iiiName", email)
                    Log.d("iiiName", password)
                }

                override fun onCancelled(error: DatabaseError) {
                    toast("Something wrong happened!")
                }
            })
    }
}
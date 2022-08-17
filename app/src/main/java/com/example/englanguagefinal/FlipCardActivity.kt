package com.example.englanguagefinal

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.englanguagefinal.extensions.launchActivity

class FlipCardActivity : AppCompatActivity() {
    private lateinit var imgBack: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card)
        supportActionBar?.hide()

        imgBack = findViewById(R.id.imgBack)
        imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
        }
    }

    override fun onBackPressed() {
        launchActivity(MainActivity::class.java)
        finish()
        super.onBackPressed()
    }
}
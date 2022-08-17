package com.example.englanguagefinal.offlinemode

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.englanguagefinal.R

class FlipCardOffActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flip_card_off)
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_flip_card_off)
        supportActionBar?.hide()
    }
}

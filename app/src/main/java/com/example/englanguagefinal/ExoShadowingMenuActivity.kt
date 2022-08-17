package com.example.englanguagefinal

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.adapter.ExoShadowingAdapter
import com.example.englanguagefinal.callbackadapter.ExoShadowingCallBack
import com.example.englanguagefinal.databinding.ActivityExoShadowingMenuBinding
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.model.exo.ShadowingList
import com.example.englanguagefinal.viewmodel.ViewModelApp

class ExoShadowingMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoShadowingMenuBinding
    private lateinit var exoShadowingAdapter: ExoShadowingAdapter
    private var viewModel: ViewModelApp = ViewModelApp()
    private val videoList: ArrayList<ShadowingList> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExoShadowingMenuBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()
        viewModel = ViewModelProvider(this)[ViewModelApp::class.java]

        binding.imgBack.setOnClickListener{
            launchActivity(MainActivity::class.java)
            finish()
        }

        viewModel.valueExoShadowing(videoList)

        binding.recyclerView.apply {
            val itemDecoration = DividerItemDecoration(this@ExoShadowingMenuActivity, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(this@ExoShadowingMenuActivity, R.drawable.divider_rcv)!!)
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(this@ExoShadowingMenuActivity)
        }

        exoShadowingAdapter = ExoShadowingAdapter(this@ExoShadowingMenuActivity, videoList)
        binding.recyclerView.adapter = exoShadowingAdapter

        exoShadowingAdapter.exoListener(object : ExoShadowingCallBack {
            override fun shadowingOnItemClickListener(url: String, name: String) {
                startActivity(url, name)
            }
        })
    }

    private fun startActivity(url: String, name: String) {
        val intent = Intent(this@ExoShadowingMenuActivity, ExoShadowingActivity::class.java)
        intent.putExtra("url", url)
        intent.putExtra("name", name)
        startActivity(intent)
    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}
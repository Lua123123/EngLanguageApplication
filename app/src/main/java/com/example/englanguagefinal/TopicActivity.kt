package com.example.englanguagefinal

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.adapter.ListTopicAdapter
import com.example.englanguagefinal.callbackadapter.ItemClickListener
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.viewmodel.ViewModelApp
import com.example.englanguagefinal.databinding.ActivityTopicBinding

class TopicActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTopicBinding
    private lateinit var adapter: ListTopicAdapter
    private val postsList: MutableList<Success> = ArrayList()
    private var viewModelApp: ViewModelApp = ViewModelApp()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopicBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.hide()

        binding.recyclerView.apply {
            val itemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_rcv)!!)
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListTopicAdapter(this@TopicActivity, postsList)

        adapter.setOnClickListener(object: ItemClickListener {
            override fun onItemClickListener(position: Int, nameTopic: String, view: View) {
                val intent = Intent(this@TopicActivity, VocabularyOfTopicActivity::class.java)
                val bundle = Bundle()
                bundle.putInt("id", position)
                bundle.putString("nameTopic", nameTopic)
                intent.putExtra("data", bundle)
                startActivity(intent)
            }
        })

        binding.recyclerView.adapter = adapter

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
        }
        binding.layoutLoading.visibility = View.VISIBLE
        viewModelApp.mClickGetTopic(postsList, adapter, binding.layoutLoading, this@TopicActivity)
    }

    override fun onBackPressed() {
        launchActivity(MainActivity::class.java)
        finish()
        super.onBackPressed()
    }
}
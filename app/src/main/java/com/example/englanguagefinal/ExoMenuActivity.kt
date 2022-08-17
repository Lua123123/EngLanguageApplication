package com.example.englanguagefinal

import android.os.Bundle
import android.content.Intent
import androidx.databinding.DataBindingUtil
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.englanguagefinal.adapter.ExoMenuAdapter
import com.example.englanguagefinal.utils.GridItemDecoration
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.callbackadapter.ExamItemClickListener
import com.example.englanguagefinal.databinding.ActivityExoMenuBinding

class ExoMenuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityExoMenuBinding
    private lateinit var adapter: ExoMenuAdapter
    private val listImageTopic: MutableList<Int> = ArrayList()
    private val listNameTopic: MutableList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exo_menu)
        binding = DataBindingUtil.setContentView(this@ExoMenuActivity, R.layout.activity_exo_menu)
        supportActionBar?.hide()

        listNameTopic.add("Contracts")
        listImageTopic.add(R.drawable.contracts)

        listNameTopic.add("Marketing")
        listImageTopic.add(R.drawable.marketing)

        listNameTopic.add("Warranties")
        listImageTopic.add(R.drawable.warranties)

        listNameTopic.add("Business planning")
        listImageTopic.add(R.drawable.business_planning)

        listNameTopic.add("Conferences")
        listImageTopic.add(R.drawable.conferences)

        listNameTopic.add("Computers and the internet")
        listImageTopic.add(R.drawable.computers_and_the_internet)

        listNameTopic.add("Office technology")
        listImageTopic.add(R.drawable.office_technology)

        listNameTopic.add("Office procedures")
        listImageTopic.add(R.drawable.office_procedures)

        listNameTopic.add("Electronics")
        listImageTopic.add(R.drawable.electronics)

        listNameTopic.add("Correspondence")
        listImageTopic.add(R.drawable.correspondence)

        listNameTopic.add("Job ads and recruitment")
        listImageTopic.add(R.drawable.job_ads_and_recruitment)

        listNameTopic.add("Apply and interviewing")
        listImageTopic.add(R.drawable.apply_and_interviewing)

        binding.recyclerView.apply {
            addItemDecoration(GridItemDecoration(spanCount = 2, spacing = 16, includeEdge = false))
            this.layoutManager = GridLayoutManager(this@ExoMenuActivity, 2, GridLayoutManager.VERTICAL, false)
        }

        adapter = ExoMenuAdapter(this@ExoMenuActivity, listNameTopic, listImageTopic)
        binding.recyclerView.adapter = adapter

        adapter.setOnClickListener(object : ExamItemClickListener {
            override fun examOnItemClickListener(word: String) {
                startActivity(word)
            }
        })

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
            finish()
        }
    }

    private fun startActivity(word: String) {
        val intent = Intent(this@ExoMenuActivity, ExoActivity::class.java)
        val bundle = Bundle()
        bundle.putString("word", word)
        intent.putExtra("data", bundle)
        startActivity(intent)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        launchActivity(MainActivity::class.java)
        finish()
    }
}
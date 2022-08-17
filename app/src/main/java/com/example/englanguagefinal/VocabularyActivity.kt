package com.example.englanguagefinal

import android.os.Bundle
import android.view.Menu
import android.view.View
import android.view.Gravity
import android.graphics.Color
import android.content.Intent
import android.content.Context
import android.app.SearchManager
import kotlin.collections.ArrayList
import android.speech.tts.TextToSpeech
import android.content.SharedPreferences
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.appcompat.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.englanguagefinal.helper.MySwipeHelperJV
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.helper.MyButtonClickListener
import com.example.englanguagefinal.adapter.ListVocabularyAdapter
import com.example.englanguagefinal.viewmodel.VocabularyViewModel
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.databinding.ActivityVocabularyBinding
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.offlinemode.vocabularyoffline.InformationDetailVocabulary

class VocabularyActivity : AppCompatActivity() {

    private lateinit var adapter: ListVocabularyAdapter
    private lateinit var authorization: String
    private lateinit var binding: ActivityVocabularyBinding
    private lateinit var vocabularyViewModel: VocabularyViewModel
    private lateinit var searchView: SearchView
    private lateinit var sharedPref: SharedPreferences
    private var mListVocabulary: MutableList<SuccessVocabulary> = ArrayList()
    private val search: String = ""
    private val swipeRefreshLayout: SwipeRefreshLayout by lazy {
        findViewById(R.id.swipeRefreshLayout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vocabulary)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_vocabulary)
        sharedPref = this.getSharedPreferences("dataAuth", Context.MODE_PRIVATE)
        authorization = sharedPref.getString("Authorization", "").toString()

        binding.imgBack.setOnClickListener {
            launchActivity(MainActivity::class.java)
            finish()
        }

        binding.recyclerView.apply {
            val itemDecoration = DividerItemDecoration(applicationContext, LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(applicationContext, R.drawable.divider_rcv)!!)
            this.addItemDecoration(itemDecoration)
            this.layoutManager = LinearLayoutManager(applicationContext)
        }
        adapter = ListVocabularyAdapter(context = this@VocabularyActivity)
        binding.recyclerView.adapter = adapter

        binding.addVocabulary.setOnClickListener {
            vocabularyViewModel.openDialogInsertVocabulary(Gravity.CENTER, authorization, this@VocabularyActivity, binding.recyclerView, adapter, search, binding.layoutLoading)
        }

        val swipeHelper: MySwipeHelperJV = object : MySwipeHelperJV(this, binding.recyclerView, 200) {
                override fun instantiateMyButton(viewHolder: RecyclerView.ViewHolder, buffer: MutableList<MyButton>) {
                    if (!searchView.isIconified) {
                        searchView.isIconified = true
                        searchView.isIconified = true
                    }
                    buffer.add(
                        MyButton(Gravity.CENTER, this@VocabularyActivity, "Delete", 50, 0, Color.parseColor("#2e3648"), object : MyButtonClickListener {
                                override fun onClick(pos: Int) {
                                    val position: Int = viewHolder.adapterPosition
                                    vocabularyViewModel.openDialogDeleteVocabulary(Gravity.CENTER, authorization, position, this@VocabularyActivity, adapter)
                                }
                            }
                        )
                    )
                }
            }

        swipeRefreshLayout.setOnRefreshListener {
            swipeRefreshLayout.isRefreshing = false
            vocabularyViewModel.mClickGetVocabulary(binding.recyclerView, adapter, this@VocabularyActivity, search, binding.layoutLoading)
            adapter.reload(mListVocabulary)
        }


        vocabularyViewModel = VocabularyViewModel()
        binding.layoutLoading.visibility = View.VISIBLE
        vocabularyViewModel.mClickGetVocabulary(binding.recyclerView, adapter, this@VocabularyActivity, search, binding.layoutLoading)

        adapter.setOnClickListener(object : OneVocabularyItemClickListener {
            override fun informationDetailVocabularyOnItemClickListener(textWord: String, position: Int, mListVocabulary: MutableList<SuccessVocabulary>) {
                val informationDetailVocabulary = InformationDetailVocabulary(textWord, position, this@VocabularyActivity, mListVocabulary, mTTS = TextToSpeech(this@VocabularyActivity) {})
                informationDetailVocabulary.informationDetailVocabulary()
            }

            override fun vocabularyToOneVocabularyOnItemClickListener(word: String, mean: String, view: View) {
                startActivity(word, mean)
            }
        })
    }

    private fun startActivity(word: String, mean: String) {
        val intent = Intent(this@VocabularyActivity, OneVocabularyActivity::class.java)
        intent.putExtra("word", word)
        intent.putExtra("mean", mean)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.search, menu)
        val searchManager = getSystemService(SEARCH_SERVICE) as SearchManager
        searchView = menu.findItem(R.id.action_search).actionView as SearchView
        searchView.setSearchableInfo(searchManager.getSearchableInfo(componentName))
        searchView.maxWidth = Int.MAX_VALUE
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(s: String): Boolean {
                adapter.filter.filter(s)
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                adapter.filter.filter(s)
                return false
            }
        })
        return true
    }

    override fun onBackPressed() {
        if (!searchView.isIconified) {
            searchView.isIconified = true
            searchView.isIconified = true
            return
        }
        launchActivity(MainActivity::class.java)
        finish()
        super.onBackPressed()
    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
        adapter.release()
    }
}
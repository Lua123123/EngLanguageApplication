package com.example.englanguagefinal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.databinding.ListVocabularyItemBinding
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import java.util.*

class ListVocabularyAdapter(private var context: Context?) :
    RecyclerView.Adapter<ListVocabularyAdapter.ViewHolder>(), Filterable {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private lateinit var itemClickListener: OneVocabularyItemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf()) {
        this.mListVocabulary = mListVocabulary
        mListVocabularyOld = mListVocabulary
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun reload(mListVocabulary: List<SuccessVocabulary>?) {
        this.mListVocabulary.clear()
        this.mListVocabulary.addAll(mListVocabulary!!)
        notifyDataSetChanged()
    }

    fun setOnClickListener(itemClickListener: OneVocabularyItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListVocabularyItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val textWord = mListVocabulary[position].word
        holder.tvWord.text = mListVocabulary[position].word
        holder.tvMean.text = mListVocabulary[position].mean
        holder.tvExample.text = mListVocabulary[position].example
        val word = mListVocabulary[position].word
        val mean = mListVocabulary[position].mean

        holder.imgDetail.setOnClickListener {
            itemClickListener.informationDetailVocabularyOnItemClickListener(
                textWord,
                position,
                mListVocabulary
            )
        }

        holder.layoutItem.setOnClickListener { view ->
            itemClickListener.vocabularyToOneVocabularyOnItemClickListener(word, mean, view)
        }

    }

    override fun getItemCount(): Int {
        return mListVocabulary.size
    }

    inner class ViewHolder(binding: ListVocabularyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val tvWord = binding.word
        val tvMean = binding.mean
        val tvExample = binding.example
        val imgDetail = binding.imgDetail
        val layoutItem = binding.layoutItem
    }

    fun release() {
        context = null
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(charSequence: CharSequence): FilterResults {
                val strSearch = charSequence.toString()
                mListVocabulary = if (strSearch.isEmpty()) {
                    mListVocabularyOld
                } else {
                    val list: MutableList<SuccessVocabulary> = ArrayList()
                    for (successVocabulary in mListVocabularyOld) {
                        if (successVocabulary.word.lowercase().contains(strSearch.lowercase())) {
                            list.add(successVocabulary)
                        }
                    }
                    list
                }
                val filterResults = FilterResults()
                filterResults.values = mListVocabulary
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(charSequence: CharSequence, filterResults: FilterResults) {
                mListVocabulary =
                    (filterResults.values as MutableList<SuccessVocabulary>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }
}
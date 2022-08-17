package com.example.englanguagefinal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.databinding.ListVocabularyOfTopicItemBinding

class ListVocabularyOfTopicAdapter(
    private val context: Context, private val postsList: List<SuccessVocabulary>?) : RecyclerView.Adapter<ListVocabularyOfTopicAdapter.ListVocabularyOfTopicViewHolder>() {
    private lateinit var itemClickListener: OneVocabularyItemClickListener

    fun setOnClickListener(itemClickListener: OneVocabularyItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListVocabularyOfTopicViewHolder {
        return ListVocabularyOfTopicViewHolder(
            ListVocabularyOfTopicItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ListVocabularyOfTopicViewHolder, position: Int) {
        val successVocabulary = postsList!![position]
        val word = postsList[position].word
        val mean = postsList[position].mean
        holder.tvWord.text = successVocabulary.word
        holder.tvMean.text = successVocabulary.mean

        holder.layoutItem.setOnClickListener { view ->
            itemClickListener.vocabularyToOneVocabularyOnItemClickListener(word, mean, view)
        }
    }

    override fun getItemCount(): Int {
        return postsList?.size ?: 0
    }

    class ListVocabularyOfTopicViewHolder(binding: ListVocabularyOfTopicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvWord = binding.word
        val tvMean = binding.mean
        var layoutItem = binding.layoutItem
    }
}

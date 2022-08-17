package com.example.englanguagefinal.offlinemode.vocabularyoffline

import android.annotation.SuppressLint
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.R
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import kotlin.collections.ArrayList

class ListVocabularyAdapterOffline : RecyclerView.Adapter<ListVocabularyAdapterOffline.ViewHolder>(), Filterable {
    private var mListVocabulary = mutableListOf<SuccessVocabulary>()
    private var mListVocabularyOld = mutableListOf<SuccessVocabulary>()
    private lateinit var itemClickListener: OneVocabularyItemClickListener

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mListVocabulary: MutableList<SuccessVocabulary> = mutableListOf<SuccessVocabulary>()) {
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
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_vocabulary_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val successVocabulary = mListVocabulary[position] ?: return
        if (successVocabulary != null || mListVocabulary != null) {
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
    }

    override fun getItemCount(): Int {
        return if (mListVocabulary != null) {
            mListVocabulary.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvWord: TextView
        val tvMean: TextView
        val tvExample: TextView
        val imgDetail: ImageView
        val layoutItem: LinearLayout

        init {
            tvWord = itemView.findViewById(R.id.word)
            tvMean = itemView.findViewById(R.id.mean)
            tvExample = itemView.findViewById(R.id.example)
            imgDetail = itemView.findViewById(R.id.img_detail)
            layoutItem = itemView.findViewById(R.id.layout_item)
        }
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
                mListVocabulary = (filterResults.values as List<SuccessVocabulary>).toMutableList()
                notifyDataSetChanged()
            }
        }
    }
}
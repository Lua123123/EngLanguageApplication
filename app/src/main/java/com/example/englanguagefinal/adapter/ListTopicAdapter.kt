package com.example.englanguagefinal.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.content.Context
import com.example.englanguagefinal.model.topic.Success
import com.example.englanguagefinal.callbackadapter.ItemClickListener
import com.example.englanguagefinal.databinding.ListTopicItemBinding

class ListTopicAdapter(private val context: Context, private val postsList: List<Success>?) : RecyclerView.Adapter<ListTopicAdapter.ViewHolder>() {
    private lateinit var itemClickListener: ItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListTopicItemBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    fun setOnClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        val success = postsList!![position]
        val nameTopic = postsList[position].name
        val id = postsList[position].id
        holder.tvTitle.text = success.name
        holder.tvMount.text = success.soluong.toString()
        holder.itemTopic.setOnClickListener { view ->
            itemClickListener.onItemClickListener(id, nameTopic, view)
        }
    }

    override fun getItemCount(): Int {
        return postsList?.size ?: 0
    }

    class ViewHolder(binding: ListTopicItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTitle = binding.topic
        val tvMount = binding.amount
        var itemTopic = binding.itemTopic
    }
}
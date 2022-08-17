package com.example.englanguagefinal.adapter

import android.view.ViewGroup
import android.view.LayoutInflater
import android.annotation.SuppressLint
import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.callbackadapter.ExamItemClickListener
import com.example.englanguagefinal.databinding.ListItemExoTopicBinding

class ExoMenuAdapter(private val context: Context, private val name: List<String>, private val images: List<Int>) : RecyclerView.Adapter<ExoMenuAdapter.ViewHolder>() {
    private lateinit var itemClickListener: ExamItemClickListener

    fun setOnClickListener(itemClickListener: ExamItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    class ViewHolder(binding: ListItemExoTopicBinding) : RecyclerView.ViewHolder(binding.root) {
        val tvTopic = binding.tvTopic
        val imgTopic = binding.imgTopic
        val layoutCardView = binding.layoutCardView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ListItemExoTopicBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {
        holder.tvTopic.text = name[position]
        holder.imgTopic.setImageResource(images[position])
        holder.layoutCardView.setOnClickListener {
            itemClickListener.examOnItemClickListener(name[position])
        }
    }

    override fun getItemCount(): Int {
        return name.size
    }
}
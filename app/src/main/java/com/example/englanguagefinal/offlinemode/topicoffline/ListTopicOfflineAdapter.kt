package com.example.englanguagefinal.offlinemode.topicoffline

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.R
import com.example.englanguagefinal.callbackadapter.ItemClickListener
import com.example.englanguagefinal.model.topic.Success

class ListTopicOfflineAdapter(private var postsList: List<Success>?, private val context: Context) : RecyclerView.Adapter<ListTopicOfflineAdapter.ViewHolder>() {
    private var itemClickListener: ItemClickListener? = null

    fun setData(postsList: List<Success>?) {
        this.postsList = postsList
        notifyDataSetChanged()
    }

    fun setOnClickListener(itemClickListener: ItemClickListener) {
        this.itemClickListener = itemClickListener
    }

    @SuppressLint("InflateParams")
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.list_topic_item, null))
    }

    @SuppressLint("CommitPrefEdits")
    override fun onBindViewHolder(holder: ViewHolder, @SuppressLint("RecyclerView") position: Int) {

        val success = postsList!![position]
        val nameTopic = postsList!![position].name
        holder.tvTitle.text = success.name
        holder.tvMount.text = success.soluong.toString()
        holder.itemTopic.setOnClickListener { view ->
            itemClickListener?.onItemClickListener(position, nameTopic, view)
        }
    }

    override fun getItemCount(): Int {
        return if (postsList != null) {
            postsList!!.size
        } else 0
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView
        val tvMount: TextView
        val itemTopic: LinearLayout

        init {
            tvTitle = itemView.findViewById(R.id.topic)
            tvMount = itemView.findViewById(R.id.amount)
            itemTopic = itemView.findViewById(R.id.item_topic)
        }
    }
}
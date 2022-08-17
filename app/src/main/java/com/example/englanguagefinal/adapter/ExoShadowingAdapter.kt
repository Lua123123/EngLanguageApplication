package com.example.englanguagefinal.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.englanguagefinal.callbackadapter.ExoShadowingCallBack
import com.example.englanguagefinal.databinding.LayoutExoShadowingBinding
import com.example.englanguagefinal.model.exo.ShadowingList

class ExoShadowingAdapter(private val context: Context, private val videoList: ArrayList<ShadowingList>) : RecyclerView.Adapter<ExoShadowingAdapter.ExoShadowingViewHolder>() {
    private lateinit var exoCallBack: ExoShadowingCallBack

    fun exoListener(exoCallBack: ExoShadowingCallBack) {
        this.exoCallBack = exoCallBack
    }

    class ExoShadowingViewHolder(binding: LayoutExoShadowingBinding) : RecyclerView.ViewHolder(binding.root) {
        val title = binding.videoName
        val duration = binding.duration
        val image = binding.imgVideo
        val layout = binding.layoutVideo
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExoShadowingViewHolder {
        return ExoShadowingViewHolder(LayoutExoShadowingBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExoShadowingViewHolder, position: Int) {
        Glide.with(context).load(videoList[position].image).into(holder.image)
        holder.title.text = videoList[position].name
        holder.duration.text = videoList[position].duration
        val url = videoList[position].url
        holder.layout.setOnClickListener {
            exoCallBack.shadowingOnItemClickListener(url, videoList[position].name)
        }
    }
    override fun getItemCount(): Int {
        return videoList.size
    }
}
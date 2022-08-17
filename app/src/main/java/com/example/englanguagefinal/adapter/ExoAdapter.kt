package com.example.englanguagefinal.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.example.englanguagefinal.model.exo.MediaObjects
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.callbackadapter.ExoCallBack
import com.example.englanguagefinal.databinding.LayoutExoBinding

class ExoAdapter(private val context: Context, private val mediaObjectsList: List<MediaObjects>) : RecyclerView.Adapter<ExoAdapter.ExoViewHolder>() {
    private lateinit var exoCallBack: ExoCallBack
    private lateinit var mp4: String

    fun exoListener(exoCallBack: ExoCallBack) {
        this.exoCallBack = exoCallBack
    }

    class ExoViewHolder(binding: LayoutExoBinding) : RecyclerView.ViewHolder(binding.root) {
        var playerView = binding.exoplayerView
        var textVideo = binding.textVideo
        var tvComment = binding.tvComment
        var tvVocabulary = binding.tvVocabulary
        var tvTuVung = binding.tvTuVung
        var videoTitle = binding.videoTitle
        var btnBack = binding.btnBack
        var btnLock = binding.btnLock
        var btnFullVideo = binding.btnFullVideo
        var topController = binding.topController
        var bottomController = binding.bottomController
        var layoutCardView = binding.layoutCardView

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExoViewHolder {
        return ExoViewHolder(LayoutExoBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ExoViewHolder, position: Int) {
        mp4 = mediaObjectsList[position].mp4.toString()
        holder.textVideo.text = mediaObjectsList[position].textVideo
        holder.tvComment.text = mediaObjectsList[position].tvComment
        holder.tvVocabulary.text = mediaObjectsList[position].tvVocabulary
        holder.videoTitle.text = mediaObjectsList[position].textVideo
        holder.tvTuVung.text = "#tuvung, #vocabulary"

        exoCallBack.exoCallBack(mp4, holder.playerView, holder.videoTitle, holder.btnBack, holder.btnFullVideo, holder.topController, holder.bottomController, holder.layoutCardView, holder.btnLock, holder.textVideo)
    }
    override fun getItemCount(): Int {
        return mediaObjectsList.size
    }
}
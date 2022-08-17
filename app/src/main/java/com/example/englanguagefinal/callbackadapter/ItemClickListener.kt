package com.example.englanguagefinal.callbackadapter

import android.view.View
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.google.android.exoplayer2.ui.PlayerView

interface ItemClickListener {
    fun onItemClickListener(position: Int, nameTopic: String, view: View)
}
interface OneVocabularyItemClickListener {
    fun informationDetailVocabularyOnItemClickListener(textWord: String, position: Int, mListVocabulary: MutableList<SuccessVocabulary>)
    fun vocabularyToOneVocabularyOnItemClickListener(word: String, mean: String, view: View)
}
interface ExamItemClickListener {
    fun examOnItemClickListener(word: String)
}

interface ExoCallBack {
    fun exoCallBack(mp4: String, playerView: PlayerView, videoTitle: TextView, btnBack: ImageButton, btnFullVideo: ImageButton, topController: LinearLayout, bottomController: LinearLayout, layoutCardView: ConstraintLayout, btnLock: ImageButton, textVideo: TextView)
}

interface ExoShadowingCallBack {
    fun shadowingOnItemClickListener(url: String, name: String)
}
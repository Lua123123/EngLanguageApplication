package com.example.englanguagefinal.model.vocabulary

import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

class Vocabulary {
    @SerializedName("success")
    @Expose
    var success: List<SuccessVocabulary>? = null
}
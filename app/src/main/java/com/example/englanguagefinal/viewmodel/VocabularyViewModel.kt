package com.example.englanguagefinal.viewmodel

import retrofit2.Call
import android.view.View
import retrofit2.Callback
import retrofit2.Response
import android.app.Dialog
import android.view.Window
import kotlinx.coroutines.*
import android.view.Gravity
import android.graphics.Color
import android.widget.TextView
import android.widget.EditText
import android.content.Context
import android.view.WindowManager
import androidx.lifecycle.ViewModel
import com.example.englanguagefinal.R
import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import android.graphics.drawable.ColorDrawable
import com.example.englanguagefinal.network.API
import androidx.recyclerview.widget.RecyclerView
import com.example.englanguagefinal.extensions.toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.model.vocabulary.Vocabulary
import com.example.englanguagefinal.adapter.ListVocabularyAdapter
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import com.example.englanguagefinal.model.vocabulary.DeleteVocabulary
import com.example.englanguagefinal.model.vocabulary.SuccessInsertVocabulary

class VocabularyViewModel : ViewModel() {
    private var mListVocabulary: MutableList<SuccessVocabulary> = ArrayList()
    private var vocabulary: Vocabulary? = null
    private val errorMessage = MutableLiveData<String>()
    private val loading = MutableLiveData<Boolean>()
    private lateinit var job: Job
    private lateinit var word: String

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        onError("Exception handled: ${throwable.localizedMessage}")
    }

    private fun onError(message: String) {
        errorMessage.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun openDialogDeleteVocabulary(gravity: Int, Authorization: String?, position: Int, context: Context, adapter: ListVocabularyAdapter) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom_delete)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val tvVocabulary = dialog.findViewById<TextView>(R.id.tv_vocabulary)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        word = vocabulary?.success?.get(position)?.word!!
        tvVocabulary.text = word
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            clickDeleteVocabulary(Authorization, word, position, context, adapter)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clickDeleteVocabulary(Authorization: String?, word: String?, position: Int, context: Context, adapter: ListVocabularyAdapter) {
        API.api.deleteVocabulary(Authorization, 1, word)?.enqueue(object : Callback<DeleteVocabulary?> {
                @SuppressLint("NotifyDataSetChanged")
                override fun onResponse(call: Call<DeleteVocabulary?>, response: Response<DeleteVocabulary?>) {
                    mListVocabulary.removeAt(position)
                    adapter.notifyDataSetChanged()
                }

                override fun onFailure(call: Call<DeleteVocabulary?>, t: Throwable) {
                    context.toast("DELETE VOCABULARY FAILED!")
                }
            })
    }

    fun openDialogInsertVocabulary(gravity: Int, Authorization: String?, context: Context, recyclerView: RecyclerView, adapter: ListVocabularyAdapter, search: String, layoutLoading: ConstraintLayout) {
        val dialog = Dialog(context)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.dialog_custom)
        val window = dialog.window ?: return
        window.setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT)
        window.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val windowAttributes = window.attributes
        windowAttributes.gravity = gravity
        window.attributes = windowAttributes
        if (Gravity.CENTER == gravity) {
            dialog.setCancelable(true)
        } else {
            dialog.setCancelable(false)
        }
        val edtWord = dialog.findViewById<EditText>(R.id.edt_word)
        val edtMean = dialog.findViewById<EditText>(R.id.edt_mean)
        val edtExample = dialog.findViewById<EditText>(R.id.edt_example)
        val btnCancel = dialog.findViewById<ConstraintLayout>(R.id.btn_cancel)
        val btnConfirm = dialog.findViewById<ConstraintLayout>(R.id.btn_confirm)
        btnCancel.setOnClickListener { dialog.dismiss() }
        btnConfirm.setOnClickListener {
            val word = edtWord.text.toString().trim { it <= ' ' }
            val mean = edtMean.text.toString().trim { it <= ' ' }
            val example = edtExample.text.toString().trim { it <= ' ' }
            clickInsertVocabulary(Authorization, word, mean, example, context, recyclerView, adapter, search, layoutLoading)
            dialog.dismiss()
        }
        dialog.show()
    }

    private fun clickInsertVocabulary(Authorization: String?, word: String?, mean: String?, example: String?, context: Context, recyclerView: RecyclerView, adapter: ListVocabularyAdapter, search: String, layoutLoading: ConstraintLayout) {
        API.api.insertVocabulary(Authorization, word, mean, "image_path", example, 2, 1)
            ?.enqueue(object : Callback<SuccessInsertVocabulary?> {
                override fun onResponse(call: Call<SuccessInsertVocabulary?>, response: Response<SuccessInsertVocabulary?>) {
                    mListVocabulary.removeAll(mListVocabulary)
                    mClickGetVocabulary(recyclerView, adapter, context, search, layoutLoading)
                    context.toast("Insert successfully")
                }

                override fun onFailure(call: Call<SuccessInsertVocabulary?>, t: Throwable) {
                    context.toast("INSERT VOCABULARY FAILED!")
                }
            })
    }

    fun mClickGetVocabulary(recyclerView: RecyclerView, adapter: ListVocabularyAdapter, context: Context, search: String, layoutLoading: ConstraintLayout) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            mGetVocabulary(recyclerView, adapter, context, search, layoutLoading)
        }
    }

    private fun mGetVocabulary(recyclerView: RecyclerView, adapter: ListVocabularyAdapter, context: Context, search: String, layoutLoading: ConstraintLayout) {
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            clickGetVocabulary(recyclerView, adapter, context, search, layoutLoading)
        }
    }

    private fun clickGetVocabulary(recyclerView: RecyclerView, adapter: ListVocabularyAdapter, context: Context, search: String, layoutLoading: ConstraintLayout): List<SuccessVocabulary>? {
        API.api.getVocabulary(1, search, "30")?.enqueue(object : Callback<Vocabulary?> {
            @SuppressLint("NotifyDataSetChanged")
            override fun onResponse(call: Call<Vocabulary?>, response: Response<Vocabulary?>) {
                vocabulary = response.body()
                mListVocabulary.removeAll(mListVocabulary)
                for (i in vocabulary?.success?.indices!!) {
                    val successVocabulary = SuccessVocabulary(0, vocabulary?.success?.get(i)?.word!!, vocabulary?.success?.get(i)?.mean!!, vocabulary?.success?.get(i)?.example!!)
                    mListVocabulary.add(successVocabulary)
                    adapter.setData(mListVocabulary)
                    recyclerView.adapter = adapter
                    adapter.notifyDataSetChanged()

                    if (VocabularyDatabase.getInstance(context).vocabularyDAO()?.getListVocabulary()?.size!! <= mListVocabulary.size) {
                        val strWord = vocabulary?.success?.get(i)?.word!!
                        val strMean = vocabulary?.success?.get(i)?.mean!!
                        val strExample = vocabulary?.success?.get(i)?.example!!
                        val successDataRoom = SuccessVocabulary(0, strWord, strMean, strExample)
                        VocabularyDatabase.getInstance(context).vocabularyDAO()?.insertVocabulary(successDataRoom)
                    }
                    layoutLoading.visibility = View.GONE
                }
            }

            override fun onFailure(call: Call<Vocabulary?>, t: Throwable) {
                context.toast("SHOW LIST VOCABULARY FAILED")
                layoutLoading.visibility = View.GONE
            }
        })
        return null
    }
}
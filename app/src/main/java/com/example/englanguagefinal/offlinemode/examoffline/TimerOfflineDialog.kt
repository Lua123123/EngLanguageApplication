package com.example.englanguagefinal.offlinemode.examoffline

import android.app.Dialog
import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.graphics.Color
import android.view.View
import android.widget.Button
import androidx.navigation.NavController
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.R

class TimerOfflineDialog(private val mContext: Context, private val findNavController: NavController) {
    private lateinit var timerDialog: Dialog
    fun timerDialog() {
        timerDialog = Dialog(mContext)
        timerDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        timerDialog.setContentView(R.layout.time_up_dialog)
        val btnTimerDialog = timerDialog.findViewById<View>(R.id.btn_timerDialog) as Button
        btnTimerDialog.setOnClickListener {
            findNavController.navigate(R.id.action_quizOffFragment_to_startQuizOfflineFragment)
            timerDialog.dismiss()
        }
        timerDialog.show()
        timerDialog.setCancelable(false)
        timerDialog.setCanceledOnTouchOutside(false)
    }
}
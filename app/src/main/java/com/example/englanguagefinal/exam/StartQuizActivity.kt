package com.example.englanguagefinal.exam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import com.example.englanguagefinal.MainActivity
import com.example.englanguagefinal.extensions.launchActivity
import com.example.englanguagefinal.R
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.databinding.ActivityStartQuizBinding
import java.util.*

class StartQuizActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartQuizBinding
    private var quizViewModel: QuizViewModel = QuizViewModel(context = this@StartQuizActivity)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_quiz)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_start_quiz)
        supportActionBar?.hide()
        binding.btnContract.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataContract()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnMarketing.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataMarketing()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnWarranties.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataWarranties()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnBusiness.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataBusiness()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnConferences.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataConferences()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnComputers.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataComputers()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnOfficeTech.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeTech()
            launchActivity(QuizActivity::class.java)
            finish()
        }

        binding.btnOfficeProducts.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeProducts()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnElectronics.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataElectronics()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnCorres.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataCorrespondence()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnJob.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataJob()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnApply.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataApply()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnHiring.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataHiring()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnSalary.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataSalary()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnPromotions.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataPromotions()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnShopping.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataShopping()
            launchActivity(QuizActivity::class.java)
            finish()
        }
        binding.btnOder.setOnClickListener {
            VocabularyDatabase.getInstance(this).questionDao().deleteAllQuestions()
            quizViewModel.addDataOder()
            launchActivity(QuizActivity::class.java)
            finish()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        launchActivity(MainActivity::class.java)
        finish()
    }
}
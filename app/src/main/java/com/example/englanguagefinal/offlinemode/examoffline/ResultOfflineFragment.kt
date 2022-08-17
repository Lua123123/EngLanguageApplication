package com.example.englanguagefinal.offlinemode.examoffline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.findNavController
import com.example.englanguagefinal.R
import com.example.englanguagefinal.databinding.FragmentResultOfflineBinding

class ResultOfflineFragment : Fragment() {
    private lateinit var binding: FragmentResultOfflineBinding
    private var highScore = 0

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentResultOfflineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.resultBtnMainMenu.setOnClickListener {
            findNavController().navigate(R.id.action_resultOfflineFragment_to_startQuizOfflineFragment)
        }
        binding.resultBtnPlayAgain.setOnClickListener {
            findNavController().navigate(R.id.action_resultOfflineFragment_to_quizOffFragment)
        }

        loadHighScore()

        val score = arguments?.getInt("UserScore")
        val totalQuestion = arguments?.getInt("TotalQuizQuestions")
        val correctQuestion = arguments?.getInt("CorrectQuestions")
        val wrongQuestion = arguments?.getInt("WrongQuestions")

        binding.resultTvTotalQuestion.text = "Total Questions: $totalQuestion"
        binding.resultTvCorrect.text = "Correct Questions: $correctQuestion"
        binding.resultTvWrong.text = "Wrong Questions: $wrongQuestion"
        if (score!! > highScore) {
            updateScore(score)
        }
    }

    private fun updateScore(score: Int) {
        highScore = score
        binding.resultTvHighScore.text = "High Score: $highScore"
        val sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(SHARED_PREFERENCE_HIGH_SCORE, highScore)
        editor.apply()
    }

    private fun loadHighScore() {
        val sharedPreferences = requireActivity().getSharedPreferences(SHARED_PREFERENCE, AppCompatActivity.MODE_PRIVATE)
        highScore = sharedPreferences.getInt(SHARED_PREFERENCE_HIGH_SCORE, 0)
        binding.resultTvHighScore.text = "High Score: $highScore"
    }

    companion object {
        private const val SHARED_PREFERENCE = "shared_preference"
        private const val SHARED_PREFERENCE_HIGH_SCORE = "shared_preference_high_score"
    }
}
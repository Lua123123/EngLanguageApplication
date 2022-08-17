package com.example.englanguagefinal.offlinemode.examoffline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.englanguagefinal.R
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.databinding.FragmentStartQuizOfflineBinding
import com.example.englanguagefinal.exam.QuizViewModel

class StartQuizOfflineFragment : Fragment() {
    private lateinit var binding: FragmentStartQuizOfflineBinding
    private lateinit var quizViewModel: QuizViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStartQuizOfflineBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        quizViewModel = QuizViewModel(requireActivity())

        binding.btnContract.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataContract()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnMarketing.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataMarketing()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnWarranties.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataWarranties()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnBusiness.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataBusiness()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnConferences.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataConferences()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnComputers.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataComputers()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnOfficeTech.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeTech()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }

        binding.btnOfficeProducts.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataOfficeProducts()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnElectronics.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataElectronics()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnCorres.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataCorrespondence()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnJob.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataJob()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnApply.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataApply()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnHiring.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataHiring()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnSalary.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataSalary()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnPromotions.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataPromotions()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnShopping.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataShopping()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
        binding.btnOder.setOnClickListener {
            VocabularyDatabase.getInstance(requireActivity()).questionDao().deleteAllQuestions()
            quizViewModel.addDataOder()
            findNavController().navigate(R.id.action_startQuizOfflineFragment_to_quizOffFragment)
        }
    }
}
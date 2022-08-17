package com.example.englanguagefinal.offlinemode.vocabularyoffline

import android.os.Bundle
import android.speech.tts.TextToSpeech
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.englanguagefinal.databinding.FragmentVocabularyBinding
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.R
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary
import kotlin.collections.ArrayList

class VocabularyFragment : Fragment() {
    private lateinit var binding: FragmentVocabularyBinding
    private lateinit var adapter: ListVocabularyAdapterOffline
    private var mListVocabulary: List<SuccessVocabulary> = ArrayList()
    private var mTTS: TextToSpeech? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVocabularyBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgBackOffline.setOnClickListener {
            findNavController().navigate(R.id.action_vocabularyFragment_to_mainFragment)
        }

        binding.recyclerViewOffline.apply {
            val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.divider_rcv)!!)
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(requireActivity())
        }

        mListVocabulary = VocabularyDatabase.getInstance(requireActivity()).vocabularyDAO()?.getListVocabulary() as MutableList<SuccessVocabulary>
        adapter = ListVocabularyAdapterOffline()
        adapter.setData(mListVocabulary as MutableList<SuccessVocabulary>)
        binding.recyclerViewOffline.adapter = adapter

        adapter.setOnClickListener(object : OneVocabularyItemClickListener {
            override fun informationDetailVocabularyOnItemClickListener(textWord: String, position: Int, mListVocabulary: MutableList<SuccessVocabulary>) {
                val informationDetailVocabulary = InformationDetailVocabulary(textWord, position, requireActivity(), mListVocabulary, mTTS = TextToSpeech(context) {})
                informationDetailVocabulary.informationDetailVocabulary()
            }

            override fun vocabularyToOneVocabularyOnItemClickListener(word: String, mean: String, view: View) {
                val bundle = bundleOf("word" to word, "mean" to mean)
                findNavController().navigate(R.id.action_vocabularyFragment_to_oneVocabularyOffFragment, bundle)
            }
        })

        //LOAD AGAIN
        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.swipeRefreshLayout.isRefreshing = false
            mListVocabulary = VocabularyDatabase.getInstance(requireActivity()).vocabularyDAO()?.getListVocabulary() as MutableList<SuccessVocabulary>
            adapter.reload(mListVocabulary)
        }
    }
}
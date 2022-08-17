package com.example.englanguagefinal.offlinemode.vocabularyoftopicoffline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.R
import com.example.englanguagefinal.callbackadapter.OneVocabularyItemClickListener
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.databinding.FragmentVocabularyOfTopicOffBinding
import com.example.englanguagefinal.model.vocabulary.SuccessVocabulary

class VocabularyOfTopicOffFragment : Fragment() {
    private lateinit var adapter: ListVocabularyOfTopicAdapterOffline
    private lateinit var binding: FragmentVocabularyOfTopicOffBinding
    private var postsList: List<SuccessVocabulary> = ArrayList()
    private var postsListTotal: List<SuccessVocabulary> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentVocabularyOfTopicOffBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tvTopic.text = arguments?.getString("nameTopic")
        val position = arguments?.getInt("position")

        binding.rcvVocabularyOfTopic.apply {
            val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.divider_rcv)!!)
            this.addItemDecoration(itemDecoration)
            this.layoutManager = LinearLayoutManager(requireActivity())
        }
        adapter = ListVocabularyOfTopicAdapterOffline()
        binding.rcvVocabularyOfTopic.adapter = adapter

        adapter.setOnClickListener(object : OneVocabularyItemClickListener {
            override fun informationDetailVocabularyOnItemClickListener(textWord: String, position: Int, mListVocabulary: MutableList<SuccessVocabulary>) {}

            override fun vocabularyToOneVocabularyOnItemClickListener(word: String, mean: String, view: View) {
                val bundle = bundleOf("word" to word, "mean" to mean)
                findNavController().navigate(R.id.action_vocabularyOfTopicOffFragment_to_oneVocabularyOffFragment, bundle)
            }
        })

        postsList = VocabularyDatabase.getInstance(requireActivity()).vocabularyOfTopicDAO()?.getListVocabularyOfTopic() as MutableList<SuccessVocabulary>
        if (position == 1) {
            postsListTotal = listOf(
                postsList[0],
                postsList[1],
                postsList[2],
                postsList[3],
                postsList[4],
                postsList[5],
                postsList[6],
                postsList[7],
                postsList[8],
                postsList[9],
                postsList[10],
                postsList[11]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }

        if (position == 2) {
            postsListTotal = listOf(
                postsList[12],
                postsList[13],
                postsList[14],
                postsList[15],
                postsList[16],
                postsList[17],
                postsList[18],
                postsList[19],
                postsList[20],
                postsList[21],
                postsList[22],
                postsList[23]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 3) {
            postsListTotal = listOf(
                postsList[24],
                postsList[25],
                postsList[26],
                postsList[27],
                postsList[28],
                postsList[29],
                postsList[30],
                postsList[31],
                postsList[32],
                postsList[33],
                postsList[34],
                postsList[35]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 4) {
            postsListTotal = listOf(
                postsList[36],
                postsList[37],
                postsList[38],
                postsList[39],
                postsList[40],
                postsList[41],
                postsList[42],
                postsList[43],
                postsList[44],
                postsList[45],
                postsList[46],
                postsList[47]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 5) {
            postsListTotal = listOf(
                postsList[48],
                postsList[49],
                postsList[50],
                postsList[51],
                postsList[52],
                postsList[53],
                postsList[54],
                postsList[55],
                postsList[56],
                postsList[57],
                postsList[58],
                postsList[59]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 6) {
            postsListTotal = listOf(
                postsList[60],
                postsList[61],
                postsList[62],
                postsList[63],
                postsList[64],
                postsList[65],
                postsList[66],
                postsList[67],
                postsList[68],
                postsList[69],
                postsList[70],
                postsList[71]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 7) {
            postsListTotal = listOf(
                postsList[72],
                postsList[73],
                postsList[74],
                postsList[75],
                postsList[76],
                postsList[77],
                postsList[78],
                postsList[79],
                postsList[80],
                postsList[81],
                postsList[82],
                postsList[83]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 8) {
            postsListTotal = listOf(
                postsList[84],
                postsList[85],
                postsList[86],
                postsList[87],
                postsList[88],
                postsList[89],
                postsList[90],
                postsList[91],
                postsList[92],
                postsList[93],
                postsList[94],
                postsList[95]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
        if (position == 9) {
            postsListTotal = listOf(
                postsList[96],
                postsList[97],
                postsList[98],
                postsList[99],
                postsList[100],
                postsList[101],
                postsList[102],
                postsList[103],
                postsList[104],
                postsList[105],
                postsList[106],
                postsList[107]
            )
            adapter.setData(postsListTotal as MutableList<SuccessVocabulary>, requireActivity())
            binding.rcvVocabularyOfTopic.adapter = adapter
        }
    }
}
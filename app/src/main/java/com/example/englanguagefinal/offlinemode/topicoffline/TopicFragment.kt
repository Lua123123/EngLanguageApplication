package com.example.englanguagefinal.offlinemode.topicoffline

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import com.example.englanguagefinal.databinding.FragmentTopicBinding
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.englanguagefinal.R
import com.example.englanguagefinal.callbackadapter.ItemClickListener
import com.example.englanguagefinal.database.VocabularyDatabase
import com.example.englanguagefinal.model.topic.Success

class TopicFragment : Fragment() {
    private lateinit var adapter: ListTopicOfflineAdapter
    private lateinit var binding: FragmentTopicBinding
    private var postsList: MutableList<Success> = ArrayList()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_topic, container, false)
        binding.lifecycleOwner = this
        val view = binding.root
        view.findViewById<ConstraintLayout>(R.id.imgBackOffline).setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_topicFragment_to_mainFragment)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = ListTopicOfflineAdapter(postsList, requireActivity())

        adapter.setOnClickListener(object : ItemClickListener {
            override fun onItemClickListener(position: Int, nameTopic: String, view: View) {
                val bundle = bundleOf("nameTopic" to nameTopic, "position" to position + 1)
                findNavController().navigate(R.id.action_topicFragment_to_vocabularyOfTopicOffFragment, bundle)
            }
        })

        binding.recyclerViewOffline.apply {
            val itemDecoration = DividerItemDecoration(requireActivity(), LinearLayoutManager.VERTICAL)
            itemDecoration.setDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.divider_rcv)!!)
            addItemDecoration(itemDecoration)
            layoutManager = LinearLayoutManager(requireActivity())
        }

        postsList = VocabularyDatabase.getInstance(requireActivity()).topicDAO()?.getListTopic() as MutableList<Success>
        adapter.setData(postsList)
        binding.recyclerViewOffline.adapter = adapter
    }
}
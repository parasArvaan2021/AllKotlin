package com.main.kotlin.icst.recentlyadded

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.recentlyadded.adapter.RecentlyAddedListAdapter

class RecentlyAddedFragment:Fragment() {
    private val listOfRecyclerData = ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_recently_added, container, false)
        val recycler: RecyclerView = view.findViewById(R.id.frgRecentlyRecyclerView)
        for (i in 0..7) {
            listOfRecyclerData.add("paras")
        }


        val adapter = RecentlyAddedListAdapter(requireActivity(), listOfRecyclerData)
        val layout = GridLayoutManager(requireActivity(), 2)
        recycler.layoutManager = layout
        recycler.adapter = adapter

        return view
    }
}
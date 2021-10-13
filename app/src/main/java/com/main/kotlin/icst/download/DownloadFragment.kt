package com.main.kotlin.icst.download

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.download.adapter.DownloadListAdapter

class DownloadFragment : Fragment() {


    private val listOfRecyclerData = ArrayList<String>()

    // This property is only valid between onCreateView and

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        val view: View = inflater.inflate(R.layout.fragment_download, container, false)


        for (i in 0..7) {
            listOfRecyclerData.add("paras")
        }

        val recycler: RecyclerView = view.findViewById(R.id.frgDownloadRecyclerView)

        recycler.layoutManager = GridLayoutManager(requireActivity(), 2)
        recycler.adapter = DownloadListAdapter(requireActivity(), listOfRecyclerData)

        return view
    }

}
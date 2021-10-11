package com.main.kotlin.fragmentwithbottomnavigation.ui.home.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.fragmentwithbottomnavigation.ui.home.list.adapter.HomeListAdapter
import com.main.kotlin.fragmentwithbottomnavigation.ui.home.list.adapter.HomeListData

class HomeFragment : Fragment() {
   private var listOfRecyclerData: List<HomeListData> = listOf()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)
        val recyclerContinueWatching: RecyclerView =
            view.findViewById(R.id.recycleFrgHomeContinueWatching)
        listOfRecyclerData = setData()
        Log.i("paras", "onCreateView: $listOfRecyclerData")
        val adapterContinueWatching = HomeListAdapter(requireActivity(), listOfRecyclerData)
        recyclerContinueWatching.layoutManager=LinearLayoutManager(requireActivity())
        recyclerContinueWatching.setHasFixedSize(true)
        recyclerContinueWatching.hasFixedSize()
        recyclerContinueWatching.adapter = adapterContinueWatching
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
    }

    private fun setData(): List<HomeListData> {
        val list = listOf(
            HomeListData(
                "Continue Watching",
                listOf(
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "12th March 21",
                        "patient story",
                        "Georgia my long covid",
                        30,
                        "30 min"
                    ),
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    ),
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    )

                )
            ),
            HomeListData(
                "Most Popular",
                listOf(
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "12th March 21",
                        "patient story",
                        "Georgia my long covid",
                        30,
                        "30 min"
                    ),
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    ),
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__480.jpg",
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    )

                )
            )
        )
        return list
    }
}
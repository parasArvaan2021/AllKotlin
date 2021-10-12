package com.main.kotlin.icst.home.list

import android.opengl.Visibility
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.home.list.adapter.HomeListAdapter
import com.main.kotlin.icst.home.list.adapter.HomeListData

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
                        "12th January 21",
                        "patient story",
                        "Georgia my long covid",
                        30,
                        "30 min"
                    ),
                    HomeListData.Data(
                        "https://cdn.pixabay.com/photo/2018/01/14/23/12/nature-3082832__480.jpg",
                        "14th September 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    ),
                    HomeListData.Data(
                        "https://i.pinimg.com/originals/a4/96/c2/a496c2b6bc5d7cfe0e0674f6598c38ad.jpg",
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        50,
                        "50 min"
                    )

                )
            ),
            HomeListData(
                "Most Popular",
                listOf(
                    HomeListData.Data(
                        "https://i.pinimg.com/originals/9d/bb/7b/9dbb7b1fee6b77fcfd20cb2b9023701f.jpg",
                        "12th December 21",
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
                        "https://cdn.pixabay.com/photo/2021/08/25/20/42/field-6574455__480.jpg",
                        "14th September 21",
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
package com.main.kotlin.icst.home.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.common.BaseFragment
import com.main.kotlin.icst.home.list.adapter.HomeListAdapter
import com.main.kotlin.icst.home.list.adapter.HomeListData

class HomeFragment : BaseFragment() {



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_home, container, false)

        val btnWatch:LinearLayout=view.findViewById(R.id.llFrgHomeWatch)

        btnWatch.setOnClickListener(View.OnClickListener {

                    navigateToOtherFragment(R.id.actionHomeToVideoPlayer,null)
        })
        val recyclerContinueWatching: RecyclerView =
            view.findViewById(R.id.recycleFrgHomeContinueWatching)

        val adapterContinueWatching = HomeListAdapter(requireActivity(),setData())

        recyclerContinueWatching.layoutManager=LinearLayoutManager(requireActivity())
        recyclerContinueWatching.adapter = adapterContinueWatching


        return view
    }

    private fun setData(): List<HomeListData> {
        val list = listOf(
            HomeListData(
                "Continue Watching",
                listOf(
                    HomeListData.Data(
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
                        "12th January 21",
                        "patient story",
                        "Georgia my long covid",
                        30,
                        "30 min"
                    ),
                    HomeListData.Data(
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
                        "14th September 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    ),
                    HomeListData.Data(
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
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
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
                        "12th December 21",
                        "patient story",
                        "Georgia my long covid",
                        30,
                        "30 min"
                    ),
                    HomeListData.Data(
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
                        "14th March 21",
                        "patient story",
                        "Georgia my long covid",
                        80,
                        "50 min"
                    ),
                    HomeListData.Data(
                        ContextCompat.getDrawable(requireContext(),R.drawable.sunset),
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
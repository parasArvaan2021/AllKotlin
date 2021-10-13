package com.main.kotlin.icst.home.videoplayer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.home.videoplayer.adapter.VideoPlayerListAdapter


class VideoPlayerFragment : Fragment() {
    private val listOfRecyclerData=ArrayList<String>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_video_player, container, false)

        val rvVideoPlayer: RecyclerView = view.findViewById(R.id.recuclerFrgVideoPlayer)
        rvVideoPlayer.layoutManager = GridLayoutManager(requireActivity(), 2)


        for (i in 0..7){
            listOfRecyclerData.add("paras")
        }
        rvVideoPlayer.adapter=VideoPlayerListAdapter(requireActivity(),listOfRecyclerData)

        return view
    }

}
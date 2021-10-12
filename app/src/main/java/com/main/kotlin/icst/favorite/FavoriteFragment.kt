package com.main.kotlin.icst.favorite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.icst.favorite.adapter.FavouritesListAdapter


class FavoriteFragment : Fragment() {
    private val listOfRecyclerData = ArrayList<String>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_favorite, container, false)
        val recycler: RecyclerView = view.findViewById(R.id.frgFavouriteRecyclerView)
        for (i in 0..7) {
            listOfRecyclerData.add("paras")
        }


        val adapter = FavouritesListAdapter(requireActivity(), listOfRecyclerData)
        val layout = GridLayoutManager(requireActivity(), 2)
        recycler.layoutManager = layout
        recycler.adapter = adapter

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

    }
}
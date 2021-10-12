package com.main.kotlin.icst.favorite.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R

class FavouritesListAdapter(val context:Context,val listOfData:ArrayList<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View= LayoutInflater.from(context).inflate(R.layout.item_favourite_list,parent,false)
        return ViewHolderBindLayout(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder =holder as ViewHolderBindLayout
    }

    override fun getItemCount(): Int {
        return listOfData.size
    }

    inner class ViewHolderBindLayout(itemView: View) : RecyclerView.ViewHolder(itemView){

    }
}
package com.main.kotlin.icst.home.videoplayer.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R

class VideoPlayerListAdapter(val context:Context,val setDataInList:List<String>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view:View=LayoutInflater.from(context).inflate(R.layout.item_video_player_list,parent,false)
        return ViewHolderBind(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

    }

    override fun getItemCount(): Int {
        return setDataInList.size
    }
    inner class ViewHolderBind(itemView: View): RecyclerView.ViewHolder(itemView){

    }
}
package com.main.kotlin.icst.home.list.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R

class HomeListAdapter(val context:Context, private val listOfData:List<HomeListData>):RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View= LayoutInflater.from(context).inflate(R.layout.item_home_list,parent,false)
        return ViewHolderBindLayout(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.i("paras", "onBindViewHolder: $listOfData")
        val viewHolder=holder as ViewHolderBindLayout
        val pos=listOfData[position]
        viewHolder.tvHomeFrgHeader.text= pos.heading

        val adapter= HomeListChildAdapter(context,listOfData[position].data)
        viewHolder.recyclerOfHome.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
        viewHolder.recyclerOfHome.adapter=adapter

        if(position==listOfData.lastIndex){
            viewHolder.line.visibility=View.GONE
        }

    }

    override fun getItemCount(): Int {
       return listOfData.size
    }

    inner class ViewHolderBindLayout(itemView: View) : RecyclerView.ViewHolder(itemView){
        val recyclerOfHome:RecyclerView=itemView.findViewById(R.id.homeListRecycler)
        val tvHomeFrgHeader:TextView=itemView.findViewById(R.id.tvHomeListHeader)
        val line:View=itemView.findViewById(R.id.frgHomeLine)

    }
}
package com.main.kotlin.icst.home.list.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar
import com.bumptech.glide.Glide
import com.main.kotlin.R

class HomeListChildAdapter(val context: Context, private val listOfData: List<HomeListData.Data>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_home_child_list, parent, false)
        return ViewHolderBindLayout(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as ViewHolderBindLayout
        val pos = listOfData[position]

        Glide.with(context)
            .load(pos.imageUrl)
            .placeholder(R.drawable.ic_heart)
            .error(R.drawable.eggs)
            .into(viewHolder.ivFrgHomeImage)

        viewHolder.header.text = pos.header
        viewHolder.description.text = pos.description
        viewHolder.date.text = pos.date
        viewHolder.duration.text = pos.duration
        viewHolder.progressBar.setProgress(pos.progress)

    }

    override fun getItemCount(): Int {
        return listOfData.size
    }

    inner class ViewHolderBindLayout(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ivFrgHomeImage = itemView.findViewById(R.id.ivFrgHomeImage) as ImageView
        val header: TextView = itemView.findViewById(R.id.tvFrgHomeCatagory)
        val date: TextView = itemView.findViewById(R.id.tvFrgHomeDate)
        val description: TextView = itemView.findViewById(R.id.tvFrgHomeDescription)
        val duration: TextView = itemView.findViewById(R.id.tvFrgHomeMinute)
        val progressBar: RoundCornerProgressBar = itemView.findViewById(R.id.progressBarFrgHome)

    }
}
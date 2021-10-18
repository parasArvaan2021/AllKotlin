package com.main.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.InterfaceRecyclerView
import com.main.kotlin.R

class AdapterRecyclerView(val context: Context, val size: ArrayList<String>,val viewInterface: InterfaceRecyclerView): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view:View=LayoutInflater.from(context).inflate(R.layout.layout_recyclerview,parent,false)
        return ViewHolder1(view)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val viewHolder1=holder as ViewHolder1
            val uri = size[position]
            viewHolder1.ShowPath.text=uri
//          Glide.with(context)
//                    .load(uri)
//                    .placeholder(R.drawable.ic_heart)
//                    .error(R.drawable.eggs)
//                    .into(viewHolder1.ShowPath)

            viewHolder1.Delete.setOnClickListener(View.OnClickListener {
                viewInterface.delete(position)
            })
        viewHolder1.view.setOnClickListener(View.OnClickListener {
                    viewInterface.showImage(uri.toString())
        })
    }

    override fun getItemCount(): Int {
       return size.count()
    }
    inner class ViewHolder1( itemView: View):RecyclerView.ViewHolder(itemView){
        val ShowPath:TextView=itemView.findViewById(R.id.ChoosePic)
        val Delete:ImageView=itemView.findViewById(R.id.ImageDelete)
        val view:ImageView=itemView.findViewById(R.id.ImageView)

    }
}



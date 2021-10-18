package com.main.kotlin.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.main.kotlin.R
import com.main.kotlin.api.apiexample.model.PostsModel

class ApiExampleAdapter(val context: Context) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var listOfData:List<PostsModel> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view: View =
            LayoutInflater.from(context).inflate(R.layout.item_apiexample_list, parent, false)
        return ViewHolderBindLayout(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val holderBind = holder as ViewHolderBindLayout

        holderBind.tvShowId.text = "Id : " + listOfData[position].id.toString()
        holderBind.tvShowUserId.text = "UserId : " + listOfData[position].userId.toString()
        holderBind.tvShowtitle.text = "Title : " + listOfData[position].title
        holderBind.tvShowbody.text = "Body : " + listOfData[position].body

        holderBind.itemView.setOnClickListener {
            Toast.makeText(context, "${
                "Id : " + listOfData[position].id.toString()+"\n"+
                "UserId : " + listOfData[position].userId.toString()+"\n"+
                "Title : " + listOfData[position].title+"\n"+
                "Body : " + listOfData[position].body}", Toast.LENGTH_LONG).show()
        }

    }

    override fun getItemCount(): Int {

        return listOfData.size
    }

    fun setDataInList(list:List<PostsModel>) {
        if (list != null) {
            this.listOfData = list
            Log.i("paras", "setDataInList: $listOfData")
        }
        notifyDataSetChanged()


    }



    inner class ViewHolderBindLayout(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvShowId: TextView = itemView.findViewById(R.id.tvApiId)
        val tvShowUserId: TextView = itemView.findViewById(R.id.tvApiUserId)
        val tvShowtitle: TextView = itemView.findViewById(R.id.tvApititle)
        val tvShowbody: TextView = itemView.findViewById(R.id.tvApiBoby)
    }
}
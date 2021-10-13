package com.main.kotlin.icst.home.list.adapter

import android.graphics.drawable.Drawable

data class HomeListData(val heading:String,val data:List<Data>) {
data class Data(
    val imageUrl: Drawable?,
    val date:String,
    val header: String,
    val description:String,
    val progress:Int,
    val duration:String)
}
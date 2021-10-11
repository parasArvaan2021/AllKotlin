package com.main.kotlin.fragmentwithbottomnavigation.ui.home.list.adapter

import android.provider.ContactsContract

data class HomeListData(val heading:String,val data:List<Data>) {
data class Data(val imageUrl:String,
                val date:String,
                val header: String,
                val description:String,
                val progress:Int,
                val duration:String)
}
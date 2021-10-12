package com.main.kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.viewpager.widget.PagerAdapter
import com.main.kotlin.R

class CustomPageAdapter(val contex: Context, val name: List<String>) : PagerAdapter() {
    override fun getCount(): Int {
            return name.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
       return view===`object` as View
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val view=LayoutInflater.from(contex).inflate(R.layout.layout_viewpager_fragment,container,false)
        val tvUpper:TextView=view.findViewById(R.id.txt_askme)
        tvUpper.text=name.get(position)
        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
       container.removeView(`object` as View)
    }

}
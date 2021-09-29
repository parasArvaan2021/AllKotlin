package com.main.kotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.bumptech.glide.Glide

import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.core.text.bold
import androidx.viewpager.widget.ViewPager


class EmbieDesign : AppCompatActivity() {
    val name= listOf<String>("paras","AjayBhai","KevalBhai","JayBhai")
    lateinit var viewPager : ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_embie_design)

        val imgCardViewOne:ImageView=findViewById(R.id.GifCardViewOne)
        val imgCardViewTwo:ImageView=findViewById(R.id.GifCardViewTwo)
        val imgCardViewThree:ImageView=findViewById(R.id.GifCardViewThree)
        val showText:TextView=findViewById(R.id.TxtTitle)
        val nextButton:ImageView=findViewById(R.id.ButtonNext)

        viewPager=findViewById(R.id.ViewPager)
        viewPager.adapter = CustomPageAdapter(this,name)


       Glide.with(this).load(R.raw.stims).into(imgCardViewOne)
        Glide.with(this).load(R.raw.wanda).into(imgCardViewTwo)
        Glide.with(this).load("https://drawer.design/wp-content/uploads/2019/11/20-Sleep.mp4").into(imgCardViewThree)

        val s1 :String="Paras "
        val s2=resources.getString(R.string.embie_design_header)
        val s = SpannableStringBuilder()
            .append("Hii ")
            .bold { append(s1) }
            .append(",")
            .append(s2.replace("Hi Ravid,",""))
        showText.setText(s)
        nextButton.setOnClickListener(View.OnClickListener {
            var currentPosition:Int=viewPager.currentItem+1
            if (currentPosition < name.size){
                viewPager.setCurrentItem(currentPosition,true)
            }
        })
    }
}
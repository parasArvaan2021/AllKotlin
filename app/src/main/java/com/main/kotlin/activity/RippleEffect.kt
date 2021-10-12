package com.main.kotlin.activity

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.RotateAnimation
import android.view.animation.Transformation
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import com.main.kotlin.R


class RippleEffect : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ripple_effect)
        val expand_img = findViewById<ImageView>(R.id.image_expand_first)
        //val copy_img=findViewById<ImageView>(R.id.profile_image)
        val layout: RelativeLayout = findViewById(R.id.layout_items)
        val description_expand:ImageView=findViewById(R.id.image_expand_second)
        val location_expand:ImageView=findViewById(R.id.image_expand_location)
        val des_layout:RelativeLayout=findViewById(R.id.layout_description)
        val location_layout:RelativeLayout=findViewById(R.id.layout_address)
        var rotate= RotateAnimation(0f,180f)
        rotate.duration=1000

        //copy_img.setOnClickListener(View.OnClickListener {
       //     Toast.makeText(this, "Text Copied to Clipboard", Toast.LENGTH_SHORT).show()
       // })


       description_expand.setOnClickListener(View.OnClickListener{
           if (des_layout.visibility==View.VISIBLE){
               description_expand.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_more_24))
               collapse(des_layout)
               description_expand.startAnimation(rotate)

           }else{
               description_expand.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_less_24))
               expand(des_layout)
               rotate= RotateAnimation(180f,0f)
               description_expand.startAnimation(rotate)

           }
       })
        location_expand.setOnClickListener(View.OnClickListener {

            if (location_layout.visibility==View.VISIBLE){
                location_expand.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_more_24))
                collapse(location_layout)
            }else{
                location_expand.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_less_24))
                expand(location_layout)
            }
        })
        expand_img.setOnClickListener(View.OnClickListener {
            if (layout.visibility==View.VISIBLE){
                expand_img.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_more_24))
                collapse(layout)
            }else{
                expand_img.setImageDrawable(resources.getDrawable(R.drawable.ic_baseline_expand_less_24))
                expand(layout)
            }
        })

    }

    fun expand(view:View) {
        if (view.visibility==View.VISIBLE) return
        val duration:Long
        val matchParent=View.MeasureSpec.makeMeasureSpec((view.parent as View).width,View.MeasureSpec.EXACTLY)
        val wrapContent=View.MeasureSpec.makeMeasureSpec(0,View.MeasureSpec.UNSPECIFIED)
        view.measure(matchParent,wrapContent)
        val targetheight=view.measuredHeight
        view.layoutParams.height=1
        view.visibility=View.VISIBLE
        duration=((targetheight / view.context.resources.displayMetrics.density)).toLong()
        view.alpha=0.0f
        view.visibility=View.VISIBLE
        view.animate().alpha(1.0f).setDuration(duration).setListener(null)

        val animation:Animation= object :Animation() {
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                view.layoutParams.height =
                    if (interpolatedTime == 1f) LinearLayout.LayoutParams.WRAP_CONTENT else (targetheight * interpolatedTime).toInt()
                view.requestLayout()
            }
        }
        animation.duration=duration
        view.startAnimation(animation)
    }
    fun collapse(view:View){
        if (view.visibility==View.GONE) return
        val durations: Long
        val initialHeight = view.measuredHeight
        val animation:Animation=object :Animation(){
            override fun applyTransformation(interpolatedTime: Float, t: Transformation?) {
                super.applyTransformation(interpolatedTime, t)
                if (interpolatedTime == 1f) {
                    view.visibility = View.GONE
                } else {
                    view.layoutParams.height =
                        initialHeight - (initialHeight * interpolatedTime).toInt()
                    view.requestLayout()
                }
            }
        }
        durations = (initialHeight / view.context.resources
            .displayMetrics.density).toLong()
        view.alpha = 1.0F
        view.animate().alpha(0.0f).setDuration(durations).setListener(object :
            AnimatorListenerAdapter(){
            override fun onAnimationEnd(animation: Animator) {
                super.onAnimationEnd(animation)
                view.visibility=View.GONE
                view.alpha=1.0f
            }
        })
        animation.duration=durations
        view.startAnimation(animation)
    }
}

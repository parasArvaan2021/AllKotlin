package com.main.kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.cardview.widget.CardView
import com.warkiz.tickseekbar.TickSeekBar

class CustomProgress : AppCompatActivity() {

    @SuppressLint("ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_custom_progress)
        val start_progress:Button=findViewById(R.id.start_progress)
        val value : EditText=findViewById(R.id.edt_enter_value)
        val circle_one:CardView=findViewById(R.id.img_circle1)
        val circle_two:CardView=findViewById(R.id.img_circle2)
        val circle_three:CardView=findViewById(R.id.img_circle3)
        val circle_four:CardView=findViewById(R.id.img_circle4)
        val circle_five:CardView=findViewById(R.id.img_circle5)
        val viewOne:View=findViewById(R.id.view1)
        val viewTwo:View=findViewById(R.id.view2)
        val viewThree:View=findViewById(R.id.view3)
        val viewFour:View=findViewById(R.id.view4)


        start_progress.setOnClickListener(View.OnClickListener {
            var text : Int? =value.text.toString().toIntOrNull()
            if (text != null) {
                when(text){
                    1->{
                        circle_one.setBackgroundColor(resources.getColor(R.color.green))
                        viewOne.setBackgroundColor(resources.getColor(R.color.green))
                        circle_two.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        viewTwo.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_three.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_four.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_five.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        viewThree.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey) )
                        viewFour.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))


                    }
                    2->{
                        circle_one.setBackgroundColor(resources.getColor(R.color.green))
                        circle_two.setBackgroundColor(resources.getColor(R.color.green))
                         viewOne.setBackgroundColor(resources.getColor(R.color.green))
                        viewTwo.setBackgroundColor(resources.getColor(R.color.green))
                        circle_three.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_four.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_five.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        viewThree.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey) )
                        viewFour.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))

                    }
                    3->{
                        circle_one.setBackgroundColor(resources.getColor(R.color.green))
                        circle_two.setBackgroundColor(resources.getColor(R.color.green))
                        circle_three.setBackgroundColor(resources.getColor(R.color.green))
                        viewOne.setBackgroundColor(resources.getColor(R.color.green))
                        viewTwo.setBackgroundColor(resources.getColor(R.color.green))
                        viewThree.setBackgroundColor(resources.getColor(R.color.green) )
                        circle_four.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                        circle_five.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                         viewFour.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))

                    }
                    4->{
                        circle_one.setBackgroundColor(resources.getColor(R.color.green))
                        circle_two.setBackgroundColor(resources.getColor(R.color.green))
                        circle_three.setBackgroundColor(resources.getColor(R.color.green))
                        viewOne.setBackgroundColor(resources.getColor(R.color.green))
                        viewTwo.setBackgroundColor(resources.getColor(R.color.green))
                        circle_four.setBackgroundColor(resources.getColor(R.color.green))
                        viewThree.setBackgroundColor(resources.getColor(R.color.green) )
                        viewFour.setBackgroundColor(resources.getColor(R.color.green))
                        circle_five.setBackgroundColor(resources.getColor(R.color.shap_Ovel_grey))
                    }
                    5->{
                        circle_one.setBackgroundColor(resources.getColor(R.color.green))
                        circle_two.setBackgroundColor(resources.getColor(R.color.green))
                        circle_three.setBackgroundColor(resources.getColor(R.color.green))
                        viewOne.setBackgroundColor(resources.getColor(R.color.green))
                        viewTwo.setBackgroundColor(resources.getColor(R.color.green))
                        circle_four.setBackgroundColor(resources.getColor(R.color.green))
                        viewThree.setBackgroundColor(resources.getColor(R.color.green) )
                        viewFour.setBackgroundColor(resources.getColor(R.color.green))
                        circle_five.setBackgroundColor(resources.getColor(R.color.green))
                    }
                    else->{
                        Toast.makeText(this, "envalid enter value", Toast.LENGTH_SHORT).show()
                    }
                }

            }else{
                Toast.makeText(this, "please enter value...!", Toast.LENGTH_SHORT).show()
            }
            value.setText("")
            //Log.i("value", "onCreate: $text")
        })
    }
}
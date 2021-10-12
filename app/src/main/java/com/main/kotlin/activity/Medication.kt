package com.main.kotlin.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.main.kotlin.R

class Medication : AppCompatActivity(), View.OnClickListener {

    lateinit var TimeOne: Button
    lateinit var TimeTwo: Button
    lateinit var TimeThree: Button
    lateinit var SubScribe: Button
    lateinit var UnSubScribe: Button
    lateinit var Submit: Button

    var ShowTime: Int = 0
    var SubOrUnSub = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_medication)
        TimeOne = findViewById(R.id.TxtTimeOne)
        TimeTwo = findViewById(R.id.TxtTimeTwo)
        TimeThree = findViewById(R.id.TxtTimeThree)
        SubScribe = findViewById(R.id.SubScribe)
        UnSubScribe = findViewById(R.id.UnSubScribe)
        Submit = findViewById(R.id.Submit)

        TimeOne.setOnClickListener(this)
        TimeTwo.setOnClickListener(this)
        TimeThree.setOnClickListener(this)
        SubScribe.setOnClickListener(this)
        UnSubScribe.setOnClickListener(this)
        Submit.setOnClickListener(this)

    }

    @SuppressLint("ResourceType")
    override fun onClick(p0: View?) {

        if (p0 != null) {
            when (p0.id) {
                R.id.TxtTimeOne -> {
                    ShowTime = 1
                    //Log.i("Paras", "onClick: $ShowTime")
                }
                R.id.TxtTimeTwo -> {
                    ShowTime = 2
                   // Log.i("Paras", "onClick: $ShowTime")
                }
                R.id.TxtTimeThree -> {
                    ShowTime = 3
                   // Log.i("Paras", "onClick: $ShowTime")
                }
                R.id.SubScribe -> {
                    SubOrUnSub = true
                }
                R.id.UnSubScribe -> {
                    SubOrUnSub = false
                }
                R.id.Submit -> {
                   // Log.i("Paras", "onClick: $ShowTime -> $SubOrUnSub")
                    val intent=Intent(this, EmbieMedication::class.java)
                    intent.putExtra("TimeShow",ShowTime)
                    intent.putExtra("SubOrUnSubScribe",SubOrUnSub)
                    startActivity(intent)
                }
            }
        }

    }
}
package com.main.kotlin.activity

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.main.kotlin.R


class EmbieMedication : AppCompatActivity(), AdapterView.OnItemSelectedListener {
    val bankNames = arrayOf("About", "Side Effect", "Instruction")
    var HowManyShowText: Int = 0
    var SubscribeOrNot: Boolean = false



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_embie_medication)
        val spinner: Spinner = findViewById(R.id.Spinner)
        val ShowTimeHeading: TextView = findViewById(R.id.TxtTime)
        val ShowTime: TextView = findViewById(R.id.ShowTime)
        val AmOrPm: TextView = findViewById(R.id.ShowTimeAmOrPm)
        val ShowTimeHeading1: TextView = findViewById(R.id.TxtTimeOne)
        val ShowTime1: TextView = findViewById(R.id.ShowTimeOne)
        val AmOrPm1: TextView = findViewById(R.id.ShowTimeOneAmOrPm)
        val ShowTimeHeading2: TextView = findViewById(R.id.TxtTimeTwo)
        val ShowTime2: TextView = findViewById(R.id.ShowTimeTwo)
        val AmOrPm2: TextView = findViewById(R.id.ShowTimeTwoAmOrPm)
        val line: View = findViewById(R.id.Line3)
        val Line1: View = findViewById(R.id.Line2)
        val view:ViewGroup=findViewById(R.id.LayoutChangeBackground)
        val include:ViewGroup=findViewById(R.id.LayoutUnSubScribe)
        val ShowHtmlText:TextView=findViewById(R.id.TxtDesUnlock)
        //val fileName:String = "Description.html";
        //ShowHtmlText.getSettings().setJavaScriptEnabled(true);
        //ShowHtmlText.loadUrl("file:///android_asset/" + fileName);
        val content:String="Premium includes"+
                " <li>Detailed medication uses, videos and expected symptoms</li>"+
                " <li>Insights into your treatment cycle in real time.</li>"+
                " <li>Alerts, when something looks 'off'</li>"+
                "<li>Tailored to you recommendations by diagnosis, protocol type, etc.</li>" +
                "<li>Access to hundreds of resources, videos &amp; more</li>"


       // ShowHtmlText.text=Html.fromHtml(content,Html.FROM_HTML_MODE_LEGACY)
        var extra: Bundle? = intent.extras
        if (extra != null) {
            SubscribeOrNot = extra.getBoolean("SubOrUnSubScribe")
            HowManyShowText = extra.getInt("TimeShow")

            Log.i("Kansagara", "onCreate: $SubscribeOrNot    $HowManyShowText")
        }

        spinner.onItemSelectedListener = this
        val aa: ArrayAdapter<*> =
            ArrayAdapter<Any?>(this, android.R.layout.simple_spinner_item, bankNames)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(aa)

        if(SubscribeOrNot){
            view.visibility=View.VISIBLE
        }else{
            include.visibility=View.VISIBLE


        }

        when (HowManyShowText) {
            1 -> {
                ShowTimeHeading.visibility=View.VISIBLE
                ShowTime.visibility=View.VISIBLE
                AmOrPm.visibility=View.VISIBLE

                ShowTimeHeading1.visibility=View.GONE
                ShowTime1.visibility=View.GONE
                AmOrPm1.visibility=View.GONE
                Line1.visibility=View.GONE

                ShowTimeHeading2.visibility=View.GONE
                ShowTime2.visibility=View.GONE
                AmOrPm2.visibility=View.GONE
                line.visibility=View.GONE
            }
            2->{
                ShowTimeHeading.visibility=View.VISIBLE
                ShowTime.visibility=View.VISIBLE
                AmOrPm.visibility=View.VISIBLE

                ShowTimeHeading1.visibility=View.VISIBLE
                ShowTime1.visibility=View.VISIBLE
                AmOrPm1.visibility=View.VISIBLE
                Line1.visibility=View.VISIBLE

                ShowTimeHeading2.visibility=View.GONE
                ShowTime2.visibility=View.GONE
                AmOrPm2.visibility=View.GONE
                line.visibility=View.GONE
            }
            3->{
                ShowTimeHeading.visibility=View.VISIBLE
                ShowTime.visibility=View.VISIBLE
                AmOrPm.visibility=View.VISIBLE

                ShowTimeHeading1.visibility=View.VISIBLE
                ShowTime1.visibility=View.VISIBLE
                AmOrPm1.visibility=View.VISIBLE
                Line1.visibility=View.VISIBLE

                ShowTimeHeading2.visibility=View.VISIBLE
                ShowTime2.visibility=View.VISIBLE
                AmOrPm2.visibility=View.VISIBLE
                line.visibility=View.VISIBLE
            }

        }


    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

    }


    override fun onNothingSelected(p0: AdapterView<*>?) {

    }

}


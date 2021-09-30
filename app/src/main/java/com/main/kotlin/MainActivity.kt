package com.main.kotlin


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_ripple_effect: Button = findViewById(R.id.btn_ripple_effect)
        val btn_custom_progress: Button = findViewById(R.id.btn_custom_progress)
        val btnEmbieDesign: Button = findViewById(R.id.btn_embie_design)
        val BottomNavigation: Button = findViewById(R.id.BottomNavigation)
        val EmbieMedication: Button = findViewById(R.id.EmbieMedication)
        val LocalFileOpen: Button = findViewById(R.id.LocalFileOpen)
        val Fragment: Button = findViewById(R.id.FragmentExampleWithoutStateClear)
        val btnNotification: Button = findViewById(R.id.btnNotification)
        val btnNotificationTesting: Button = findViewById(R.id.btnNotificationTesting)
        val btnMoreTimeCreateNotification:Button=findViewById(R.id.btnOneORMoreTimeCreateNotification)

        btnNotificationTesting.setOnClickListener(this)
        btn_ripple_effect.setOnClickListener(this)
        btn_custom_progress.setOnClickListener(this)
        btnEmbieDesign.setOnClickListener(this)
        BottomNavigation.setOnClickListener(this)
        EmbieMedication.setOnClickListener(this)
        LocalFileOpen.setOnClickListener(this)
        Fragment.setOnClickListener(this)
        btnNotification.setOnClickListener(this)
        btnMoreTimeCreateNotification.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v != null) {
            when (v.id) {
                R.id.btn_ripple_effect -> {
                    val intent = Intent(this, RippleEffect::class.java)
                    startActivity(intent)
                }
                R.id.btn_custom_progress -> {
                    val intent = Intent(this, CustomProgress::class.java)
                    startActivity(intent)
                }
                R.id.btn_embie_design -> {
                    val intent = Intent(this, EmbieDesign::class.java)
                    startActivity(intent)
                }
                R.id.BottomNavigation -> {
                    val intent = Intent(this, BottomNavigation::class.java)
                    startActivity(intent)
                }
                R.id.EmbieMedication -> {
                    val intent = Intent(this, Medication::class.java)
                    startActivity(intent)
                }
                R.id.LocalFileOpen -> {
                    val intent = Intent(this, ChooseFile::class.java)
                    startActivity(intent)
                }
                R.id.FragmentExampleWithoutStateClear -> {
                    val intent = Intent(this, FragmentWithOutClearState::class.java)
                    startActivity(intent)
                }
                R.id.btnNotification -> {
                    val intent = Intent(this, NotificationClass::class.java)
                    startActivity(intent)
                }
                R.id.btnNotificationTesting -> {
                    val intent = Intent(this, TestingNotification::class.java)
                    startActivity(intent)
                }
                R.id.btnOneORMoreTimeCreateNotification->{
                    val intent=Intent(this,GenerateNotification::class.java)
                    startActivity(intent)
                }
            }
        }
    }


}
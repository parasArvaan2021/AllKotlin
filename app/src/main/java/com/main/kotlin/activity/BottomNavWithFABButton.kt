package com.main.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.main.kotlin.R

class BottomNavWithFABButton : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_nav_with_fabbutton)
        val bottomNavigationView: BottomNavigationView =findViewById(R.id.bottomNavigationView)
//
//        bottomNavigationView.background = null
//        bottomNavigationView.menu.getItem(2).isEnabled = false
    }
}
package com.main.kotlin.fragmentwithbottomnavigation.ui

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.main.kotlin.R

class BottomNavigationWithFragment : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_bottom_navigation_with_fragment)

        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController =
            findNavController(R.id.nav_host_fragment_activity_bottom_navigation_with_fragment)

       AppBarConfiguration(
            setOf(
                R.id.navigation_home,
                R.id.navigation_download,
                R.id.navigation_favourite,
                R.id.navigation_setting,
                R.id.navigation_recently_added
            )
        )
        navView.setupWithNavController(navController)
    }
}
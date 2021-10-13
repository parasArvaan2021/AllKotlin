package com.main.kotlin.icst

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationMenuView
import com.main.kotlin.R
import java.lang.reflect.Field

class DashboardActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setContentView(R.layout.activity_dashboard)

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
package com.main.kotlin

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.annotation.NonNull
import com.google.android.material.bottomnavigation.BottomNavigationItemView
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.navigation.NavigationView


class BottomNavigation : AppCompatActivity() {
    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_navigation)
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottomNavigation)

        bottomNavigation.setOnNavigationItemSelectedListener(
            object : NavigationView.OnNavigationItemSelectedListener,
                BottomNavigationView.OnNavigationItemSelectedListener {
                override fun onNavigationItemSelected(@NonNull item: MenuItem): Boolean {
                    when (item.itemId) {
                        R.id.action_home->{
                                        Toast.makeText(this@BottomNavigation, "home", Toast.LENGTH_SHORT).show()
                                    }
                        R.id.action_notifications->{
                            Toast.makeText(this@BottomNavigation, "notification", Toast.LENGTH_SHORT).show()
                        }
                        R.id.action_calander->{
                            Toast.makeText(this@BottomNavigation, "calendar", Toast.LENGTH_SHORT).show()
                        }
                        R.id.action_bulb->{
                            Toast.makeText(this@BottomNavigation, "Bulb", Toast.LENGTH_SHORT).show()
                        }
                    }
                    return true
                }
            })

    }
}



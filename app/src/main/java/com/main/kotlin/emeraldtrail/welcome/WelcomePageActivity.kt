package com.main.kotlin.emeraldtrail.welcome

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.main.kotlin.R

class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val tvLinkTrailBlazer:TextView=findViewById(R.id.tvWelcomeTrailBlazerAccount)

        tvLinkTrailBlazer.movementMethod=LinkMovementMethod.getInstance()
    }
}
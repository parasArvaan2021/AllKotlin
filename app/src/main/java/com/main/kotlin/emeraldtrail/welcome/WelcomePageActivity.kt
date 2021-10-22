package com.main.kotlin.emeraldtrail.welcome

import android.content.Intent
import android.graphics.Color
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.SpannedString
import android.text.method.LinkMovementMethod
import android.widget.ImageView
import android.widget.TextView
import androidx.core.text.bold
import androidx.core.text.buildSpannedString
import androidx.core.text.color
import com.main.kotlin.R
import com.main.kotlin.emeraldtrail.register.RegisterEmeraldActivity

class WelcomePageActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome_page)

        val tvLinkTrailBlazer:TextView=findViewById(R.id.tvWelcomeTrailBlazerAccount)
        findViewById<ImageView>(R.id.ivWelcomeSignIn).setOnClickListener {
            val moveToSignIn=Intent(this,RegisterEmeraldActivity::class.java)
            moveToSignIn.putExtra("signIn",true)
            startActivity(moveToSignIn)
        }
        findViewById<ImageView>(R.id.ivWelcomeRegister).setOnClickListener {
            val moveToRegister=Intent(this,RegisterEmeraldActivity::class.java)
            moveToRegister.putExtra("signIn",false)
            startActivity(moveToRegister)
        }

     /*   val string: SpannedString = buildSpannedString {
            append("bar ")
            color(Color.BLUE) {
               *//* val url = "http://www.google.com"
                val i = Intent(Intent.ACTION_VIEW)
                i.data = Uri.parse(url)
                startActivity(i)*//*
            }.append("Hakuna Matata")
        }

        tvLinkTrailBlazer.text = string*/




    }
}
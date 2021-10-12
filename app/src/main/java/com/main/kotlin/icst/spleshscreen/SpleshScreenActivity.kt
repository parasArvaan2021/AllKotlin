package com.main.kotlin.icst.spleshscreen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.main.kotlin.R
import com.main.kotlin.icst.signin.LoginActivity

class SpleshScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splesh_screen)

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
           startActivity(Intent(this, LoginActivity::class.java))
            finish()

        }, 2000)

    }


}
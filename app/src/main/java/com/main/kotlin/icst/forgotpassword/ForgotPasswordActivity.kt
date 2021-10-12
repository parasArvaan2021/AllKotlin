package com.main.kotlin.icst.forgotpassword


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.ImageView

import com.main.kotlin.R

class ForgotPasswordActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)
        findViewById<ImageView>(R.id.ivForgotPasswordBackButton).setOnClickListener {
            onBackPressed()
        }



    }
}
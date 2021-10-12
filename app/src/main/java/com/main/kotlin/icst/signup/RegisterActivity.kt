package com.main.kotlin.icst.signup

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.widget.TextView
import com.main.kotlin.R

class RegisterActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        val tvTermsAndCondition:TextView=findViewById(R.id.tvRegisterTermsAndCondition)
        val btnRegisterSignIn:TextView=findViewById(R.id.btnRegisterSignin)
        tvTermsAndCondition.movementMethod=LinkMovementMethod.getInstance()

        btnRegisterSignIn.setOnClickListener {
            finish()
        }
    }
}
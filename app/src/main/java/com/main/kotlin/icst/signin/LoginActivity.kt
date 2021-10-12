package com.main.kotlin.icst.signin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.main.kotlin.R
import com.main.kotlin.icst.DashboardActivity
import com.main.kotlin.icst.forgotpassword.ForgotPasswordActivity
import com.main.kotlin.icst.signup.RegisterActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val forgotPasswordOrEmailPageClick: TextView = findViewById(R.id.tvForgotEmailOrPassword)
        val btnSignUp:TextView=findViewById(R.id.btnSignUp)
        val btnSignIn:TextView=findViewById(R.id.btnSignIn)

        forgotPasswordOrEmailPageClick.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(this, ForgotPasswordActivity::class.java)
            )
        })
        btnSignUp.setOnClickListener(View.OnClickListener {
            startActivity(
                Intent(this, RegisterActivity::class.java)
            )
        })
        btnSignIn.setOnClickListener(View.OnClickListener {
            startActivity(Intent(this,DashboardActivity::class.java))
        })

    }
}
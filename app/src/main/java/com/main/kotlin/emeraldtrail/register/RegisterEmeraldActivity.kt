package com.main.kotlin.emeraldtrail.register

import android.R.id
import android.os.Bundle
import android.text.SpannableStringBuilder
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.core.text.color
import com.main.kotlin.R
import android.content.Intent
import android.net.Uri
import android.text.Spannable

import android.text.style.ForegroundColorSpan

import android.R.id.text2
import android.graphics.Color

import android.text.SpannableString





class RegisterEmeraldActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_emarald_new)

        val bundle: Bundle? = intent.extras
        val signInOrNot: Boolean? = bundle?.getBoolean("signIn")

        // val signInPage: ConstraintLayout = findViewById(R.id.layoutSignInPage)
//        val registerPage: ConstraintLayout = findViewById(R.id.layoutRegisterPage)
//        val lineRegister:View=findViewById(R.id.lineRegister)
//        val lineSignIn:View=findViewById(R.id.lineSignIn)
//        lineRegister.visibility=View.GONE
//        findViewById<TextView>(R.id.tvRegister).setOnClickListener {
//            registerPage.visibility= View.VISIBLE
//            lineRegister.visibility=View.VISIBLE
//           // signInPage.visibility=View.GONE
//            lineSignIn.visibility=View.GONE
//
//        }
//        findViewById<TextView>(R.id.tvSignIn).setOnClickListener {
//            registerPage.visibility= View.GONE
//            signInPage.visibility=View.VISIBLE
//            lineSignIn.visibility=View.VISIBLE
//            lineRegister.visibility=View.GONE
//        }


        val tvRegister: TextView = findViewById(R.id.tvRegister)
        val viewRegisterLine: View = findViewById(R.id.lineRegister)
        val tvSignIn: TextView = findViewById(R.id.tvSignIn)
        val viewSignInLine: View = findViewById(R.id.lineSignIn)
        val tvLinkTerms: TextView = findViewById(R.id.tvRegisterCbText)


        val includeRegister: ConstraintLayout = findViewById(R.id.includeLayoutRegister)
        val includeSignIn: ConstraintLayout = findViewById(R.id.includeLayoutSignIn)
        // val includeLayoutRegister: ConstraintLayout = includeRegister.findViewById(R.id.clIncludeRegisterContainer)
        // val includeLayoutSignIn: ConstraintLayout = includeSignIn.findViewById(R.id.clIncludeSignInContainer)
        if (signInOrNot == true) {
            includeRegister.visibility = View.GONE
            viewRegisterLine.visibility = View.GONE
            viewSignInLine.visibility = View.VISIBLE
            includeSignIn.visibility = View.VISIBLE
        } else {
            includeSignIn.visibility = View.GONE
            viewSignInLine.visibility = View.GONE
            includeRegister.visibility = View.VISIBLE
            viewRegisterLine.visibility = View.VISIBLE

        }
        tvRegister.setOnClickListener {
            includeRegister.visibility = View.VISIBLE
            includeSignIn.visibility = View.GONE
            viewRegisterLine.visibility = View.VISIBLE
            viewSignInLine.visibility = View.GONE
        }

        tvSignIn.setOnClickListener {
            includeRegister.visibility = View.GONE
            includeSignIn.visibility = View.VISIBLE
            viewSignInLine.visibility = View.VISIBLE
            viewRegisterLine.visibility = View.GONE
        }

        val spannable: Spannable = SpannableString(getString(R.string.terms_condition))

        spannable.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(this, R.color.new_color)),
            12, 31,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )

        tvLinkTerms.text =spannable
        tvLinkTerms.setOnClickListener {
            val url = "http://www.google.com"
            val i = Intent(Intent.ACTION_VIEW)
            i.data = Uri.parse(url)
            startActivity(i)
        }
    }
}
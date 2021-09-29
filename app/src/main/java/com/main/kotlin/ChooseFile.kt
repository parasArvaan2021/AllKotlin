package com.main.kotlin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class ChooseFile : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_file)
        val ChooseLocalFile:Button=findViewById(R.id.ChooseLocalFile)
        val ChooseUrl:Button=findViewById(R.id.ChooseURL)

        ChooseLocalFile.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,com.main.kotlin.ChooseLocalFile::class.java)
            startActivity(intent)
        })
        ChooseUrl.setOnClickListener(View.OnClickListener {
            val intent=Intent(this,UrlAndDownload::class.java)
            startActivity(intent)
        })
    }
}
package com.main.kotlin.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.main.kotlin.R
import com.main.kotlin.emeraldtrail.welcome.WelcomePageActivity
import com.main.kotlin.icst.spleshscreen.SpleshScreenActivity

class MainActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btn_ripple_effect: Button = findViewById(R.id.btn_ripple_effect)
        val btn_custom_progress: Button = findViewById(R.id.btn_custom_progress)
        val btnEmbieDesign: Button = findViewById(R.id.btn_embie_design)
        val BottomNavigation: Button = findViewById(R.id.BottomNavigation)
        val EmbieMedication: Button = findViewById(R.id.EmbieMedication)
        val LocalFileOpen: Button = findViewById(R.id.LocalFileOpen)
        val Fragment: Button = findViewById(R.id.FragmentExampleWithoutStateClear)
        val btnMoreTimeCreateNotification: Button =
            findViewById(R.id.btnOneORMoreTimeCreateNotification)
        val btnBottomNavWithFloating: Button =
            findViewById(R.id.btnBottomNavigationWithFloatingButton)
        val btnFragmentWithBottomNavigation: Button =
            findViewById(R.id.btnIcst)
        val btnApiExample: Button = findViewById(R.id.btnRetrofitApi)
        val btnEmeraldProject:Button=findViewById(R.id.btnEmeraldTrailProject)


        btn_ripple_effect.setOnClickListener(this)
        btn_custom_progress.setOnClickListener(this)
        btnEmbieDesign.setOnClickListener(this)
        BottomNavigation.setOnClickListener(this)
        EmbieMedication.setOnClickListener(this)
        LocalFileOpen.setOnClickListener(this)
        Fragment.setOnClickListener(this)
        btnBottomNavWithFloating.setOnClickListener(this)

        btnMoreTimeCreateNotification.setOnClickListener(this)
        btnFragmentWithBottomNavigation.setOnClickListener(this)
        btnApiExample.setOnClickListener(this)
        btnEmeraldProject.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        var intent: Intent
        if (v != null) {
            when (v.id) {
                R.id.btn_ripple_effect -> {
                    intent = Intent(this, RippleEffect::class.java)
                    startActivity(intent)
                }
                R.id.btn_custom_progress -> {
                    intent = Intent(this, CustomProgress::class.java)
                    startActivity(intent)
                }
                R.id.btn_embie_design -> {
                    startActivity(Intent(this, EmbieDesign::class.java))
                }
                R.id.BottomNavigation -> {
                    startActivity(Intent(this, BottomNavigation::class.java))
                }
                R.id.EmbieMedication -> {

                    startActivity(Intent(this, Medication::class.java))
                }
                R.id.LocalFileOpen -> {

                    startActivity(Intent(this, ChooseFile::class.java))
                }
                R.id.FragmentExampleWithoutStateClear -> {

                    startActivity(Intent(this, FragmentWithOutClearState::class.java))

                }

                R.id.btnOneORMoreTimeCreateNotification -> {

                    startActivity(Intent(this, GenerateNotification::class.java))
                }

                R.id.btnBottomNavigationWithFloatingButton -> {
                    startActivity(Intent(this, BottomNavWithFABButton::class.java))
                }
                R.id.btnIcst -> {
                    startActivity(Intent(this, SpleshScreenActivity::class.java))
                }
                R.id.btnRetrofitApi->{
                    startActivity(Intent(this@MainActivity,ApiExampleActivity::class.java))
                }
                R.id.btnEmeraldTrailProject->{
                    startActivity(Intent(this@MainActivity,WelcomePageActivity::class.java))

                }
            }
        }
    }

    private var doubleBackToExitPressedOnce = false
    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            val a = Intent(Intent.ACTION_MAIN)
            a.addCategory(Intent.CATEGORY_HOME)
            a.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(a)

        }

        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

        Handler(Looper.getMainLooper()).postDelayed(Runnable {
            doubleBackToExitPressedOnce = false
        }, 3000)
    }


}
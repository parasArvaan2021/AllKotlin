package com.main.kotlin.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.main.kotlin.fragment.OneFragment
import com.main.kotlin.R

class FragmentWithOutClearState : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fragment_with_out_clear_state)
        val frame:FrameLayout=findViewById(R.id.FrameLayout)
        val fragMent= OneFragment()

        replaceFragment(fragMent)
        val fm: FragmentManager = supportFragmentManager
       // val paras=supportFragmentManager.getBackStackEntryAt(0).name
       // getSupportFragmentManager().popBackStack(paras, FragmentManager.POP_BACK_STACK_INCLUSIVE)
      //  Log.i("paras", "onCreate: $paras")// or 'getSupportFragmentManager();'

    }

    fun replaceFragment(SomeFragment:Fragment){
        val tranction:FragmentTransaction=supportFragmentManager.beginTransaction()
        tranction.replace(R.id.FrameLayout,SomeFragment)
        tranction.commit()
    }
}
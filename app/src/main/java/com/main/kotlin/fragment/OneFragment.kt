package com.main.kotlin.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.main.kotlin.R

class OneFragment : Fragment(),View.OnClickListener{
    var editText: EditText? =null
     var show:TextView?=null
    var str:String?=null
    val TAG="Paras"


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val root:View=inflater.inflate(R.layout.fragment_one,container,false)
        val fragmentTwo:Button=root.findViewById(R.id.FragmentChange)
        editText=root.findViewById(R.id.editText)
         show=root.findViewById(R.id.txt)

        fragmentTwo.setOnClickListener(this)
        return root
    }

    override fun onClick(v: View?) {
        val fragment= TwoFragment()
        var str:String=editText?.text.toString()
        show?.text=str
        Log.i("paras", "onClick: $str")
        replaceFragment(fragment)
    }

    fun replaceFragment(SomeFragment:Fragment){
        val transaction: FragmentTransaction? =fragmentManager?.beginTransaction()
        transaction?.replace(R.id.FrameLayout,SomeFragment)
        transaction?.addToBackStack(null)
        transaction?.commit()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate: ")
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.i(TAG, "onActivityCreated: ")
        if (savedInstanceState != null) {
            str=savedInstanceState.getString(TAG)
            Log.i(TAG, "onActivityCreated: $str")
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach: ")
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: ")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume: ")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause: ")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop: ")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView: ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy: ")
    }

    override fun onDetach() {
        super.onDetach()
        Log.i(TAG, "onDetach: ")
    }
    override fun onSaveInstanceState(outState:Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(TAG,editText?.text.toString())
    }

}



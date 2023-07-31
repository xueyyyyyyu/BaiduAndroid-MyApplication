package com.baidu.androidlearn.lesson9.demos

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.baidu.androidlearn.lesson9.R

class MyFragmentActivity: FragmentActivity(), FragmentListener {

    val TAG = "MyFragmentActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_activity_view)

//        addFragment()
    }

    private fun addFragment() {
        val myFragment: Fragment = MyFragment()

        val bundle = Bundle()
        bundle.putString("key", "value")
        myFragment.arguments = bundle


        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragment_container, myFragment)
        fragmentTransaction.commit()
    }

    override fun test() {
        Log.i(TAG, "fragment btn is click")
    }

}

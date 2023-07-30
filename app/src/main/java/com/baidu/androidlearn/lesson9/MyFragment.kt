package com.baidu.androidlearn.lesson9

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.baidu.androidlearn.lesson9.FragmentListener


class MyFragment: Fragment() {

    val TAG = "MyFragment"

    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.i(TAG, "onAttach")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(TAG, "onCreate")

        if (arguments != null) {
            val value = arguments?.getString("key")
            Log.i(TAG, "activity传过来的值:$value")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_view, container, false)
        view.findViewById<Button>(R.id.fragment_btn).setOnClickListener {
            if (activity is FragmentListener) {
                (activity as FragmentListener).test()
            }
        }
        return view
    }

}
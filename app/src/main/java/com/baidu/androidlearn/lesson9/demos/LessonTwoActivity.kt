package com.baidu.androidlearn.lesson9.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.baidu.androidlearn.lesson9.R

class LessonTwoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Log.v("LessonTwoActivity","Main activity onCreate ; time = ${System.currentTimeMillis()}")
    }

    override fun onResume() {
        super.onResume()
        Log.v("LessonTwoActivity","Main activity onResume ; time = ${System.currentTimeMillis()}")
    }

    override fun onPause() {
        super.onPause()
        Log.v("LessonTwoActivity","Main activity onPause ; time = ${System.currentTimeMillis()}")
    }
}
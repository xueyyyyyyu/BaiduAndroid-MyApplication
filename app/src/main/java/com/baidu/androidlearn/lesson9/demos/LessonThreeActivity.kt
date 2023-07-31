package com.baidu.androidlearn.lesson9.demos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.baidu.androidlearn.lesson9.R

class LessonThreeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        
    }

    fun showToast(view: View) {
        Toast.makeText(this, "我被点击了", Toast.LENGTH_SHORT).show()
    }
}
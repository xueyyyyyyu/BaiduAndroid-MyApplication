package com.baidu.androidlearn.lesson5

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.widget.Button
import com.baidu.androidlearn.R


class LessonFiveActivity : Activity() {

    val TAG = "LessonFiveActivity"

    val netStateChangeReceiver = NetStateChangeReceiver()

    val serviceConnection = object: ServiceConnection {

        override fun onServiceConnected(name: ComponentName?, binder: IBinder?) {
            val myBinder: MyService.MyBinder = binder as MyService.MyBinder
            val service = myBinder.getService()
            Log.i(TAG, "onServiceConnected")
            myBinder.print()
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.i(TAG, "onServiceDisconnected")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lesson5_activity_main)

        Log.i(TAG, "onCreate")

        // 跳转到第二个activity
        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this@LessonFiveActivity, SecondActivity::class.java))
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            startActivity(Intent(this@LessonFiveActivity, MyFragmentActivity::class.java))
        }

        findViewById<Button>(R.id.btn3).setOnClickListener {
            netStateChangeReceiver.registerReceiver(this)
        }

        findViewById<Button>(R.id.btn4).setOnClickListener {
            netStateChangeReceiver.unRegisterReceiver(this)
        }

        findViewById<Button>(R.id.btn5).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            startService(intent)
        }

        findViewById<Button>(R.id.btn6).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            stopService(intent)
        }

        findViewById<Button>(R.id.btn7).setOnClickListener {
            val intent = Intent(this, MyService::class.java)
            bindService(intent, serviceConnection, Context.BIND_AUTO_CREATE)
        }

        findViewById<Button>(R.id.btn8).setOnClickListener {
            unbindService(serviceConnection)
        }
    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(TAG, "onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.i(TAG, "onPause")
    }

    override fun onStop() {
        super.onStop()
        Log.i(TAG, "onStop")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(TAG, "onRestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i(TAG, "onDestroy")
    }
}
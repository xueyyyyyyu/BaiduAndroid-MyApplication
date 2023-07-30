package com.baidu.androidlearn.lesson9

import android.app.Service
import android.content.Intent
import android.os.Binder
import android.os.IBinder
import android.util.Log

class MyService : Service() {

    val TAG = "MyService"

    inner class MyBinder: Binder(), IMyBinder {

        fun getService(): MyService {
            return this@MyService
        }

        override fun print() {
            Log.i(TAG, "print hello world!")
        }
    }

    val myBinder = MyBinder()

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        // 在此处处理服务的任务，例如启动一个线程来执行长时间运行的操作
        Log.i(TAG, "onStartCommand")
        return super.onStartCommand(intent, flags, startId)
    }

    override fun onBind(intent: Intent?): IBinder? {
        // 如果不允许绑定服务，可以返回null
        Log.i(TAG, "onBind")
        return myBinder
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.i(TAG, "onUnbind")
        return false
    }

    override fun onDestroy() {
        // 当服务被停止时，可以在这里执行一些清理工作
        Log.i(TAG, "onDestroy")
        super.onDestroy()
    }
}
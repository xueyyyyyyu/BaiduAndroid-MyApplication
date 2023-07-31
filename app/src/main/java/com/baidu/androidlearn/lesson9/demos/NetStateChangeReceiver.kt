package com.baidu.androidlearn.lesson9.demos

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.content.IntentFilter
import android.util.Log

class NetStateChangeReceiver : BroadcastReceiver() {

    val TAG = "NetStateChangeReceiver"

    private var hasRegister = false

    override fun onReceive(context: Context?, intent: Intent?) {
        if ((ConnectivityManager.CONNECTIVITY_ACTION == intent?.action)) {
            Log.i(TAG, "onReceive netStateChanged")
        }
    }

    fun registerReceiver(context: Context?) {
        if (!hasRegister) {
            val intentFilter: IntentFilter = IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
            context?.registerReceiver(this, intentFilter)
            hasRegister = true
        }

    }

    fun unRegisterReceiver(context: Context?) {
        if (hasRegister) {
            context?.unregisterReceiver(this)
            hasRegister = false
        }

    }
}
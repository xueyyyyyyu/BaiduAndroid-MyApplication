package com.baidu.androidlearn.mvvm

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.annotation.MainThread
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

private val uiHandler by lazy { Handler(Looper.getMainLooper()) }
private const val TAG = "LifecycleComponent"

/**
 * 一种仅关心生命周期、但不关心运行环境（Activity/Fragment/ViewPager/View等）的MVVM组件简单实现
 */
@MainThread
abstract class LifecycleComponent<VM : ViewModel>(
    private val lifecycleOwner: LifecycleOwner,
    override val viewModel: VM
) : IComponent<VM>, DefaultLifecycleObserver {

    init {
        addLifecycleObserver(lifecycleOwner)
    }

    private fun addLifecycleObserver(owner: LifecycleOwner) {
        val state = owner.lifecycle.currentState
        if (state.isAtLeast(Lifecycle.State.CREATED) || state == Lifecycle.State.DESTROYED) {
            // 如果生命周期状态在onCreate或之后，post一帧，保证生命周期方法回调在构造函数之后
            uiHandler.post {
                if (owner.lifecycle.currentState.isAtLeast(Lifecycle.State.CREATED)) {
                    owner.lifecycle.addObserver(this@LifecycleComponent)
                }
            }
        } else {
            owner.lifecycle.addObserver(this)
        }
    }

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        onBindViewModel(viewModel, owner)
        Log.d(TAG, "onCreate")
    }

    override fun onStart(owner: LifecycleOwner) {
        super.onStart(owner)
        Log.d(TAG, "onStart")
    }

    override fun onResume(owner: LifecycleOwner) {
        super.onResume(owner)
        Log.d(TAG, "onResume")
    }

    override fun onPause(owner: LifecycleOwner) {
        super.onPause(owner)
        Log.d(TAG, "onPause")
    }

    override fun onStop(owner: LifecycleOwner) {
        super.onStop(owner)
        Log.d(TAG, "onStop")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        lifecycleOwner.lifecycle.removeObserver(this)
        Log.d(TAG, "onDestroy")
    }

    override fun getLifecycleOwner(): LifecycleOwner = lifecycleOwner
}
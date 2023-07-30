package com.baidu.androidlearn.mvvm

import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel

interface IComponent<VM : ViewModel> : ILifecycleOwnerRef {

    val viewModel: VM
    fun onBindViewModel(viewModel: VM, owner: LifecycleOwner)
}
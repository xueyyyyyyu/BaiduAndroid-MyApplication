package com.baidu.androidlearn.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HomeViewModel : ViewModel() {

    private var count = 0

    private val _text = MutableLiveData<String>().apply {
        value = "主页"
    }
    val text: LiveData<String> = _text

    fun increment() {
        _text.value = "主页${++count}"
    }

}
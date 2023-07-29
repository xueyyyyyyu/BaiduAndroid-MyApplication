package com.baidu.androidlearn.mine

import androidx.annotation.MainThread


class MineModel {

    private var _updateTime: Long = System.currentTimeMillis()

    val updateTime get() = _updateTime

    @MainThread
    fun refresh() {
        _updateTime = System.currentTimeMillis()
    }

}
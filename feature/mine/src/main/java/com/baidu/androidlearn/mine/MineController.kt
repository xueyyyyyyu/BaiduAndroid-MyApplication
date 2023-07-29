package com.baidu.androidlearn.mine

import androidx.annotation.MainThread


@MainThread
class MineController(private val view: MineView, private val model: MineModel) {

    init {
        view.binding.root.setOnClickListener {
            model.refresh()
            view.update()
        }
    }

}
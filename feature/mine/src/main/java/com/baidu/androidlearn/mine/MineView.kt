package com.baidu.androidlearn.mine

import androidx.annotation.MainThread
import com.baidu.androidlearn.mine.databinding.FragmentMineBinding
import java.text.SimpleDateFormat
import java.util.Locale

@MainThread
class MineView(val binding: FragmentMineBinding, private val model: MineModel) {

    private val format = SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.CHINA)

    @MainThread
    fun update() {
        val formatTime = format.format(model.updateTime)
        binding.textScore.text = formatTime
    }
}
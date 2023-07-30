package com.baidu.androidlearn.lesson6

import android.app.Activity
import android.content.Intent
import android.media.SoundPool
import android.os.Bundle
import android.widget.Button
import com.baidu.androidlearn.R

class LessonSixActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.lesson6_activity_main)

        findViewById<Button>(R.id.btn1).setOnClickListener {
            startActivity(Intent(this@LessonSixActivity, ImageViewActivity::class.java))
        }

        findViewById<Button>(R.id.btn2).setOnClickListener {
            startActivity(Intent(this@LessonSixActivity, MediaPlayerDemoActivity::class.java))
        }


        val SoundPoolBuilder: SoundPool.Builder = SoundPool.Builder()
        // 设置最大并发播放音频流数量
        SoundPoolBuilder.setMaxStreams(10)
        val soundPool = SoundPoolBuilder.build()

        // 从res/raw目录加载音频文件
        val soundId = soundPool.load(this, R.raw.ring, 1)

        findViewById<Button>(R.id.btn3).setOnClickListener {
            // 播放音效
            soundPool.play(soundId, 1.0f, 1.0f, 1, 0, 1.0f)
        }

        findViewById<Button>(R.id.btn4).setOnClickListener {
            startActivity(Intent(this@LessonSixActivity, VideoViewActivity::class.java))
        }

    }

}
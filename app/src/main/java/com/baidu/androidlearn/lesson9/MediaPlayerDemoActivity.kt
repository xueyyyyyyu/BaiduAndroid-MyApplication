package com.baidu.androidlearn.lesson9

import android.app.Activity
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button


class MediaPlayerDemoActivity: Activity() {

    val mediaPlayer = MediaPlayer()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.mediaplayer_activity)

        findViewById<Button>(R.id.btn_start).setOnClickListener {
            mediaPlayer.start()
        }

        findViewById<Button>(R.id.btn_pause).setOnClickListener {
            mediaPlayer.pause()
        }

        findViewById<Button>(R.id.btn_stop).setOnClickListener {
            // 如果调用了stop后，需要重新调用prepare才能再调用start
            mediaPlayer.stop()
        }

        mediaPlayer.setDataSource(this, Uri.parse(
            "android.resource://" + packageName + "/" + R.raw.music
        ))
        mediaPlayer.setAudioAttributes(
            AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .build()
        )
        mediaPlayer.prepare()
//        mediaPlayer.prepareAsync()
        mediaPlayer.setOnPreparedListener(object : MediaPlayer.OnPreparedListener {
            override fun onPrepared(mp: MediaPlayer?) {
                // 异步准备完成回调
            }
        })
        mediaPlayer.setOnCompletionListener(object : MediaPlayer.OnCompletionListener {
            override fun onCompletion(mp: MediaPlayer?) {
            }
        })
//        mediaPlayer.setOnPreparedListener {
//            // 异步准备完成回调
//        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mediaPlayer.release()
    }
}
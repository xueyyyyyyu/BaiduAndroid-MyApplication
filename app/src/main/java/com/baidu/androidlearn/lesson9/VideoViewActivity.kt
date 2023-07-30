package com.baidu.androidlearn.lesson9

import android.app.Activity
import android.net.Uri
import android.os.Bundle
import android.widget.MediaController
import android.widget.VideoView

class VideoViewActivity: Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.video_main)
        val videoView = findViewById<VideoView>(R.id.video_view)
        val videoPath = "https://globalimg.sucai999.com/uploadfile/20211210/267440/132836000096069452.mp4"
        videoView.setVideoURI(Uri.parse(videoPath))

        // 添加MediaController
        val mediaController = MediaController(this)
        videoView.setMediaController(mediaController)
        mediaController.setAnchorView(videoView)

        videoView.start() // 播放视频
    }
}
package com.baidu.androidlearn.video

import androidx.annotation.MainThread

interface IVideoView {

    fun setPresenter(presenter: IVideoPresenter)

    @MainThread
    fun showVideos(videos: List<VideoModel>)

    @MainThread
    fun showEmptyContentView()

    @MainThread
    fun hideEmptyContentView()

}
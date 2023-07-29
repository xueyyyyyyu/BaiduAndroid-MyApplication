package com.baidu.androidlearn.video


class VideoPresenter(private val view: IVideoView): IVideoPresenter {

    private val repository = VideoRepository()

    override fun load() {
        repository.load {
            if (it.isNotEmpty()) {
                view.hideEmptyContentView()
                view.showVideos(it)
            } else {
                view.showEmptyContentView()
            }
        }
    }
}
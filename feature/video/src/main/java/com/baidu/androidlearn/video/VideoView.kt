package com.baidu.androidlearn.video

import com.baidu.androidlearn.video.databinding.FragmentVideoBinding

class VideoView(private val binding: FragmentVideoBinding): IVideoView {

    override fun setPresenter(presenter: IVideoPresenter) {
        binding.textVideo.setOnClickListener {
            presenter.load()
        }
    }

    override fun showVideos(videos: List<VideoModel>) {
        binding.textVideo.text = videos.joinToString { it.title + "\n" }
    }

    override fun showEmptyContentView() {
        binding.textVideo.text = "视频为空"
    }

    override fun hideEmptyContentView() {
        binding.textVideo.text = ""
    }
}
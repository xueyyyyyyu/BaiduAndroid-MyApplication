package com.baidu.androidlearn.video

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.baidu.androidlearn.video.databinding.FragmentVideoBinding

class VideoFragment : Fragment() {

    private var _binding: FragmentVideoBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentVideoBinding.inflate(inflater, container, false).apply {
            _binding = this
        }
        val view = VideoView(binding)
        val presenter = VideoPresenter(view)
        view.setPresenter(presenter)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
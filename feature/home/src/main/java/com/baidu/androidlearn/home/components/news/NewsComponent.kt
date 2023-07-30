package com.baidu.androidlearn.home.components.news

import NewsAdapter
import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.baidu.androidlearn.home.databinding.ComponentNewsBinding
import com.baidu.androidlearn.mvvm.LifecycleComponent

class NewsComponent(
    lifecycleOwner: LifecycleOwner,
    viewModel: NewsViewModel,
    private val binding: ComponentNewsBinding
):LifecycleComponent<NewsViewModel>(lifecycleOwner, viewModel) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        viewModel.getNews()
    }

    override fun onBindViewModel(viewModel: NewsViewModel, owner: LifecycleOwner) {
        val adapter = NewsAdapter()
        binding.newsList.adapter = adapter

        viewModel.news.observe(owner) { newsList ->
            adapter.setNewsList(newsList)
        }
    }


    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d("NewsComponent", "NewsComponent onDestroy")
    }
}

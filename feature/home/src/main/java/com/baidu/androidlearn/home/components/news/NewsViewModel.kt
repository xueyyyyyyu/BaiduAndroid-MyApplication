package com.baidu.androidlearn.home.components.news

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidu.androidlearn.news.NewsRepository
import com.baidu.androidlearn.news.NewsTopic
import kotlinx.coroutines.launch

class NewsViewModel: ViewModel() {
    private val repository = NewsRepository()

    private val _news = MutableLiveData<List<NewsTopic>>()
    val news: LiveData<List<NewsTopic>> get() = _news


    @MainThread
    fun getNews() {
        viewModelScope.launch {
            repository.getNewsTopics().collect {newsTopics ->
                _news.value = newsTopics
            }
        }
    }
}
package com.baidu.androidlearn.home.components.weather

import androidx.annotation.MainThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baidu.androidlearn.weather.WeatherRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class WeatherViewModel: ViewModel() {

    private val repository = WeatherRepository()

    private val _weather = MutableLiveData<String>().apply {
        value = "晴天"
    }
    val weather: LiveData<String> = _weather

    private val _temperature = MutableLiveData<String>().apply {
        value = "18°"
    }
    val temperature: LiveData<String> = _temperature

    /*@MainThread
    fun refreshWeather() {
        viewModelScope.launch {
            repository.getCurrentWeather().collect {
                _weather.value = it.weather
                _temperature.value = "${it.temperature}°"
            }
        }
    }*/

    @MainThread
    fun gotoWeather() {
        // todo 跳转到天气页面
        // 点击天气组件跳转到一个新的activity
    }

    @MainThread
    fun onNavigationComplete() {
        // 导航完成后，将 LiveData 的值设置为 null
        //_navigateToWeather.value = null
    }

}
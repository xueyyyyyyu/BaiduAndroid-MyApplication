package com.baidu.androidlearn.home.components.weather

import android.util.Log
import androidx.lifecycle.LifecycleOwner
import com.baidu.androidlearn.home.databinding.ComponentWeatherBinding
import com.baidu.androidlearn.mvvm.LifecycleComponent

class WeatherComponent(
    lifecycleOwner: LifecycleOwner,
    viewModel: WeatherViewModel,
    private val binding: ComponentWeatherBinding
) : LifecycleComponent<WeatherViewModel>(lifecycleOwner, viewModel) {

    override fun onCreate(owner: LifecycleOwner) {
        super.onCreate(owner)
        binding.textTemperature.setOnClickListener {
            viewModel.refreshWeather()
        }
    }

    override fun onBindViewModel(viewModel: WeatherViewModel, owner: LifecycleOwner) {
        viewModel.weather.observe(owner) {
            binding.textWeather.text = it
        }
        viewModel.temperature.observe(owner) {
            binding.textTemperature.text = it
        }
    }

    override fun onDestroy(owner: LifecycleOwner) {
        super.onDestroy(owner)
        Log.d("WeatherComponent", "WeatherComponent onDestroy")
    }
}
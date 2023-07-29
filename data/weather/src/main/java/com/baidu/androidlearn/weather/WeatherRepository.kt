package com.baidu.androidlearn.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow


class WeatherRepository {

    private var currentTemperature: Int = 18

    fun getCurrentWeather(): Flow<WeatherData> {
        return flow {
            emit(WeatherData(weather = "晴天", temperature = ++currentTemperature))
        }
    }

}
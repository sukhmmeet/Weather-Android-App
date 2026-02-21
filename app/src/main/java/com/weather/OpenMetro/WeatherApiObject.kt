package com.weather.OpenMetro

import com.weather.ApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiObject {
    private const val baseUrl = ApiConfig.WEATHER_BASE_URL
    val api : WeatherApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiInterface::class.java)
    }
}
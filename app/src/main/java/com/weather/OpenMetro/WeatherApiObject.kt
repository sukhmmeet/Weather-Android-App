package com.weather.OpenMetro

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WeatherApiObject {
    private const val baseUrl = "https://api.open-meteo.com/v1/"
    val api : WeatherApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApiInterface::class.java)
    }
}
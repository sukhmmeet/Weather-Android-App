package com.weather.OpenMetro

class WeatherRepository {
    suspend fun getWeatherData(lat : String, lon : String) : WeatherData {
        return WeatherApiObject.api.getWeatherData(lat, lon)
    }
}
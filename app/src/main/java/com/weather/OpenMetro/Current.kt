package com.weather.OpenMetro

data class Current(
    val weather_code : Int,
    val apparent_temperature: Double,
    val cloud_cover: Int,
    val interval: Int,
    val is_day: Int,
    val precipitation: Double,
    val temperature_2m: Double,
    val time: String,
    val wind_direction_10m: Int,
    val wind_speed_10m: Double
)
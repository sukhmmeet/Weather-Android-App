package com.weather.OpenMetro

data class CurrentUnits(
    val apparent_temperature: String,
    val cloud_cover: String,
    val interval: String,
    val is_day: String,
    val precipitation: String,
    val temperature_2m: String,
    val time: String,
    val wind_direction_10m: String,
    val wind_speed_10m: String
)
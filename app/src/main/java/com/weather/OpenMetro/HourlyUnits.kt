package com.weather.OpenMetro

data class HourlyUnits(
    val apparent_temperature: String,
    val cloud_cover: String,
    val precipitation: String,
    val precipitation_probability: String,
    val pressure_msl: String,
    val surface_pressure: String,
    val temperature_2m: String,
    val time: String,
    val visibility: String,
    val weather_code: String,
    val wind_direction_10m: String,
    val wind_gusts_10m: String,
    val wind_speed_10m: String
)
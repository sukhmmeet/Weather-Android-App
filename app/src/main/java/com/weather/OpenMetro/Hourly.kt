package com.weather.OpenMetro

data class Hourly(
    val apparent_temperature: List<Double>,
    val cloud_cover: List<Int>,
    val precipitation: List<Double>,
    val precipitation_probability: List<Int>,
    val pressure_msl: List<Double>,
    val surface_pressure: List<Double>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val visibility: List<Double>,
    val weather_code: List<Int>,
    val wind_direction_10m: List<Int>,
    val wind_gusts_10m: List<Double>,
    val wind_speed_10m: List<Double>
)
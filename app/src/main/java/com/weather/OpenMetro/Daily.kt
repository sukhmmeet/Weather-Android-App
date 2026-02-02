package com.weather.OpenMetro

data class Daily(
    val apparent_temperature_max: List<Double>,
    val apparent_temperature_min: List<Double>,
    val daylight_duration: List<Double>,
    val precipitation_probability_max: List<Int>,
    val precipitation_sum: List<Double>,
    val sunrise: List<String>,
    val sunset: List<String>,
    val temperature_2m_max: List<Double>,
    val temperature_2m_min: List<Double>,
    val time: List<String>,
    val uv_index_clear_sky_max: List<Double>,
    val uv_index_max: List<Double>,
    val weather_code: List<Int>,
    val wind_gusts_10m_max: List<Double>,
    val wind_speed_10m_max: List<Double>
)
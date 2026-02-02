package com.weather.OpenMetro

data class DailyUnits(
    val apparent_temperature_max: String,
    val apparent_temperature_min: String,
    val daylight_duration: String,
    val precipitation_probability_max: String,
    val precipitation_sum: String,
    val sunrise: String,
    val sunset: String,
    val temperature_2m_max: String,
    val temperature_2m_min: String,
    val time: String,
    val uv_index_clear_sky_max: String,
    val uv_index_max: String,
    val weather_code: String,
    val wind_gusts_10m_max: String,
    val wind_speed_10m_max: String
)
package com.weather.OpenMetro

import retrofit2.http.GET
import retrofit2.http.Query

//https://api.open-meteo.com/v1/forecast?
// latitude=52.52&
// longitude=13.41&
// daily=weather_code,temperature_2m_min,temperature_2m_max,sunrise,sunset,daylight_duration,precipitation_sum,precipitation_probability_max,uv_index_max,uv_index_clear_sky_max,wind_speed_10m_max,wind_gusts_10m_max,apparent_temperature_max,apparent_temperature_min
// hourly=temperature_2m,apparent_temperature,weather_code,wind_speed_10m,wind_direction_10m,precipitation,precipitation_probability,pressure_msl,surface_pressure,visibility,cloud_cover,wind_gusts_10m
// current=temperature_2m,apparent_temperature,is_day,wind_speed_10m,cloud_cover,precipitation,wind_direction_10m

interface WeatherApiInterface {
    @GET("forecast")
    suspend fun getWeatherData(
        @Query("latitude") latitude : String,
        @Query("longitude") longitude : String,
        @Query("daily") daily : String = "weather_code,temperature_2m_min,temperature_2m_max,sunrise,sunset,daylight_duration,precipitation_sum,precipitation_probability_max,uv_index_max,uv_index_clear_sky_max,wind_speed_10m_max,wind_gusts_10m_max,apparent_temperature_max,apparent_temperature_min",
        @Query("hourly") hourly: String = "temperature_2m,apparent_temperature,weather_code,wind_speed_10m,wind_direction_10m,precipitation,precipitation_probability,pressure_msl,surface_pressure,visibility,cloud_cover,wind_gusts_10m",
        @Query("current") current: String = "weather_code,temperature_2m,apparent_temperature,is_day,wind_speed_10m,cloud_cover,precipitation,wind_direction_10m"
    ) : WeatherData
}
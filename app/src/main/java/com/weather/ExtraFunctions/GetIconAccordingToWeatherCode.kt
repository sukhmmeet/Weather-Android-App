package com.weather.ExtraFunctions

import com.weather.R

fun getWeatherIcon(
    weatherCode: Int,
    isDay: Int
): Int {
    return when (weatherCode) {

        // Clear sky
        0 -> if (isDay == 1)
            R.drawable.sun
        else
            R.drawable.night

        // Mainly clear / partly cloudy
        1, 2 -> if (isDay == 1)
            R.drawable.cloudy_day
        else
            R.drawable.cloudy_night

        // Overcast
        3 -> R.drawable.clouds

        // Fog
        45, 48 -> R.drawable.fog

        // Drizzle + light rain
        in 51..63 -> R.drawable.rain

        // Heavy rain
        in 65..67 -> R.drawable.rain_wind

        // Snow
        in 71..77 -> R.drawable.snow

        // Rain showers
        in 80..82 -> R.drawable.rain

        // Thunderstorm
        95, 96, 99 -> R.drawable.thunder

        // Windy (optional use)
        10 -> R.drawable.wind

        // Fallback
        else -> R.drawable.n
    }
}


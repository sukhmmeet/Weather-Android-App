package com.weather.ExtraFunctions

fun getWeatherStatus(
    temperature: Double,
    apparentTemperature: Double,
    isDay: Int,
    windSpeed: Double,
    cloudCover: Int,
    precipitation: Double
): String {

    // 🌧️ Rain check
    if (precipitation > 0.5) {
        return if (isDay == 1) "Rainy Today" else "Rainy Night"
    }

    // ☁️ Cloud-based conditions
    if (cloudCover >= 80) {
        return "Cloudy"
    }

    if (cloudCover in 30..79) {
        return "Partly Cloudy"
    }

    // 💨 Windy condition
    if (windSpeed >= 20) {
        return "Windy"
    }

    // ☀️ / 🌙 Fallback
    return if (isDay == 1) "Sunny" else "Clear Night"
}

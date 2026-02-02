package com.weather.ExtraFunctions

import com.weather.OpenMetro.Hourly
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale
import kotlin.collections.indexOfFirst

val INDIA_ZONE = ZoneId.of("Asia/Kolkata")

fun getCurrentHourIndex(hourly: Hourly): Int {
    val now = ZonedDateTime.now(INDIA_ZONE)

    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm", Locale.ENGLISH)

    return hourly.time.indexOfFirst { timeStr ->
        val hourTime = LocalDateTime.parse(timeStr, formatter).atZone(INDIA_ZONE)
        !hourTime.isBefore(now) // first hour >= current time
    }.let { if (it == -1) 0 else it } // fallback if all hours are past
}


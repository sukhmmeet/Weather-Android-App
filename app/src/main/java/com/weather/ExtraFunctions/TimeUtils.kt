package com.weather.ExtraFunctions

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.TextStyle
import java.util.Locale

object TimeUtils {

    private val INDIA_ZONE = ZoneId.of("Asia/Kolkata")

    /* ISO-8601 → Indian 12-hour Time */

    fun dateToDay(date: String): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
        val localDate = LocalDate.parse(date, formatter)
        return localDate.dayOfWeek.getDisplayName(
            TextStyle.FULL,
            Locale.ENGLISH
        )
    }

    fun isoToIndian12Hr(isoTime: String): String {
        // Parse as LocalDateTime
        val formatterInput = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm")
        val localDateTime = LocalDateTime.parse(isoTime, formatterInput)

        // Convert to India timezone
        val indiaTime = localDateTime.atZone(ZoneId.of("UTC")) // assuming API gives UTC
            .withZoneSameInstant(INDIA_ZONE)

        // Format in 12-hour
        val formatterOutput = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        return indiaTime.format(formatterOutput)
    }

    /* ISO-8601 → Indian Date */
    fun isoToIndianDate(isoTime: String): String {
        val instant = Instant.parse(isoTime)

        val formatter = DateTimeFormatter
            .ofPattern("dd MMM yyyy", Locale.ENGLISH)
            .withZone(INDIA_ZONE)

        return formatter.format(instant)
    }

    /* Current Indian Time */
    fun getCurrentIndianTime(): String {
        val indiaTime = ZonedDateTime.now(INDIA_ZONE)

        val formatter = DateTimeFormatter
            .ofPattern("hh:mm a", Locale.ENGLISH)

        return indiaTime.format(formatter)
    }

    /* Current Indian Date */
    fun getCurrentIndianDate(): String {
        val indiaDate = ZonedDateTime.now(INDIA_ZONE)

        val formatter = DateTimeFormatter
            .ofPattern("dd MMM yyyy", Locale.ENGLISH)

        return indiaDate.format(formatter)
    }
    fun isCurrentOrFuture12HrTime(time12Hr: String): Boolean {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)

        val givenLocalTime = LocalTime.parse(time12Hr, formatter)
        val currentTime = ZonedDateTime.now(INDIA_ZONE).toLocalTime()

        return !givenLocalTime.isBefore(currentTime)
    }

    fun getTodayDay(): String {
        return ZonedDateTime
            .now(ZoneId.of("Asia/Kolkata"))
            .format(DateTimeFormatter.ofPattern("EEEE", Locale.ENGLISH))
    }
    fun getDayOrNight(time12Hr: String): Int {
        val formatter = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH)
        val localTime = LocalTime.parse(time12Hr, formatter)

        return if (localTime.hour in 6..17) 1 else 0
    }
    fun secondsToHourMinute(totalSeconds: Int): String {
        val hours = totalSeconds / 3600
        val minutes = (totalSeconds % 3600) / 60

        return String.format("%02dH %02dM", hours, minutes)
    }

}

package com.weather.ExtraFunctions

fun getWindDirectionIcon(degree: Int): String {
    return when (degree) {
        in 337..360, in 0..22 -> "⬆️"   // North
        in 23..67 -> "↗️"              // North-East
        in 68..112 -> "➡️"             // East
        in 113..157 -> "↘️"            // South-East
        in 158..202 -> "⬇️"            // South
        in 203..247 -> "↙️"            // South-West
        in 248..292 -> "⬅️"            // West
        in 293..336 -> "↖️"            // North-West
        else -> "❓"
    }
}
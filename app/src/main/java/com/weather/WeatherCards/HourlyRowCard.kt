package com.weather.WeatherCards

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.weather.ExtraFunctions.TimeUtils
import com.weather.ExtraFunctions.getWeatherIcon
import com.weather.ItemUI.HourlyCard
import com.weather.OpenMetro.Hourly


@Composable
fun HourlyRowCard(hourly: Hourly) {
    var start = 0
    var i = 0
    while(i < hourly.time.size){
        if(TimeUtils.isCurrentOrFuture12HrTime(TimeUtils.isoToIndian12Hr(hourly.time[i]))){
            start = i
            break
        }
        i++
    }
    LazyRow(
        modifier = Modifier
            .padding(8.dp)
    ) {
        items(count = hourly.time.size){ index ->
            if(index >= start - 1){
                HourlyCard(
                    time = TimeUtils.isoToIndian12Hr(hourly.time[index]),
                    icon = getWeatherIcon(
                        weatherCode = hourly.weather_code[index],
                        isDay = TimeUtils.getDayOrNight(
                            TimeUtils.isoToIndian12Hr(hourly.time[index])
                        )
                    ),
                    temp = hourly.temperature_2m[index].toString(),
                    rainChance = hourly.precipitation_probability[index].toString(),
                    windDirection = hourly.wind_direction_10m[index],
                    windSpeed = hourly.wind_speed_10m[index].toString()
                )
            }
        }
    }
}
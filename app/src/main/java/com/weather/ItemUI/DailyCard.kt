package com.weather.ItemUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.ExtraFunctions.TimeUtils
import com.weather.ExtraFunctions.TimeUtils.dateToDay
import com.weather.ExtraFunctions.getWeatherIcon
import com.weather.ExtraFunctions.getWindDirectionIcon
import com.weather.OpenMetro.Daily

@Composable
fun DailyCard(index: Int, daily: Daily) {
    Card(
        modifier = Modifier
            .padding(horizontal = 6.dp, vertical = 6.dp),
        shape = CardDefaults.elevatedShape,
        colors = CardDefaults.cardColors(

        ),
        border = BorderStroke(1.dp, Color(0xFF808080)),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(getWeatherIcon(
                        weatherCode = daily.weather_code[index],
                        isDay = 1
                    )),
                    contentDescription = "weatherIcon",
                    modifier = Modifier
                        .padding(4.dp)
                        .size(40.dp),
                    tint = Color.Unspecified
                )
                Row(
                    modifier = Modifier
                        .padding(6.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = dateToDay(daily.time[index]),
                        modifier = Modifier,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                    Text(
                        text = "${daily.temperature_2m_min[index]}°/${daily.temperature_2m_max[index]}°",
                        modifier = Modifier,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.headlineSmall.fontSize
                    )
                }
            }
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Feels Like",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "${daily.apparent_temperature_min[index]}°/${daily.apparent_temperature_max[index]}°",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleMedium.fontSize
                )
            }
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83D\uDCA7 Rain: ${daily.precipitation_probability_max[index]}%",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
                Text(
                    text = "Amount: ${daily.precipitation_sum[index]}mm",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "☀\uFE0F Sunrise: ${TimeUtils.isoToIndian12Hr(daily.sunrise[index])}",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
                Text(
                    text = "\uD83C\uDF07 Sunrise: ${TimeUtils.isoToIndian12Hr(daily.sunset[index])}",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            Text(
                text = "⏳ Daylight Duration: ${TimeUtils.secondsToHourMinute(daily.daylight_duration[index].toInt())}",
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
            HorizontalDivider()
            Row(
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "\uD83C\uDF2C\uFE0F Wind ${daily.wind_speed_10m_max[index]} Km/h",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
                Text(
                    text = "\uD83D\uDCA8 Gust ${daily.wind_gusts_10m_max[index]}",
                    modifier = Modifier,
                    fontWeight = FontWeight.Normal,
                    fontSize = MaterialTheme.typography.titleSmall.fontSize
                )
            }
            Text(
                text = "\uD83D\uDD06 UV ${daily.uv_index_max[index]} (Clear ${daily.uv_index_clear_sky_max[index]})",
                modifier = Modifier
                    .padding(6.dp)
                    .fillMaxWidth(),
                fontWeight = FontWeight.Normal,
                fontSize = MaterialTheme.typography.titleSmall.fontSize
            )
        }
    }
}


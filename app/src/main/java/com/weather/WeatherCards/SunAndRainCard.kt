package com.weather.WeatherCards

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.weather.ItemUI.CustomCard


@Composable
fun SunAndRainCard(
    sunrise : String,
    sunset : String,
    daylight : String,
    chanceRain : String,
    maxPrecipitation : String
){
    Row() {
        CustomCard(
            modifier = Modifier
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\uD83C\uDF1E Sun",
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                Text(
                    text = "\uD83C\uDF05 Sunrise: $sunrise",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "\uD83C\uDF07 Sunset: $sunset",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "☀\uFE0F Daylight: $daylight",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
        CustomCard(
            modifier = Modifier
                .weight(1f)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(all = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "\uD83C\uDF27\uFE0F Rain",
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                Text(
                    text = "\uD83D\uDCA7 Chance: ${chanceRain}%",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "\uD83D\uDCA7 Total: $maxPrecipitation mm",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}
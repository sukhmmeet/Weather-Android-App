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
fun UVAndWindCard(
    maxUV: String,
    clearSkyUV: String,
    speed: String,
    gusts: String,
    windDirectionIcon: String
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
                    text = "\uD83C\uDF1E UV Index",
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                Text(
                    text = "\uD83D\uDD06 Max UV: $maxUV",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "☀\uFE0F Clear Sky UV: $clearSkyUV",
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
                    text = "$windDirectionIcon Wind",
                    modifier = Modifier,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = MaterialTheme.typography.headlineSmall.fontSize
                )
                Text(
                    text = "\uD83C\uDF2C\uFE0F Speed: ${speed}km/h",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "\uD83D\uDCA8 Gusts: ${gusts}km/h",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}
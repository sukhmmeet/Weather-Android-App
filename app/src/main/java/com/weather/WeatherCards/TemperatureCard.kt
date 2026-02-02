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
fun TemperatureCard(
    min : String,
    max : String,
    feelMin : String,
    feelMax : String
){
    val celsiusSign = "°"
    CustomCard() {
        Column (
            modifier = Modifier
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Text(
                text = "\uD83D\uDD25 Temperature",
                modifier = Modifier,
                fontWeight = FontWeight.SemiBold,
                fontSize = MaterialTheme.typography.headlineSmall.fontSize
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceEvenly
            ) {
                Text(
                    text = "\uD83E\uDD75 Max: $max${celsiusSign}C",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "\uD83C\uDF21\uFE0F Feels like: $feelMax${celsiusSign}C",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "\uD83E\uDD76 Min: $min${celsiusSign}C",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
                Text(
                    text = "\uD83E\uDDCA Feels like: $feelMin${celsiusSign}C",
                    fontSize = MaterialTheme.typography.bodyLarge.fontSize
                )
            }
        }
    }
}
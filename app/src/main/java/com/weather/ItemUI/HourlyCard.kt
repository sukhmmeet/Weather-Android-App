package com.weather.ItemUI

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.R
import com.weather.ExtraFunctions.getWindDirectionIcon

@Composable
fun HourlyCard(
    time : String = "02 PM",
    icon : Int = R.drawable.rain,
    temp : String = "32",
    rainChance : String = "100",
    windDirection : Int = 90,
    windSpeed : String = "80"
){
    val iconWind = getWindDirectionIcon(windDirection)
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
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                modifier = Modifier
                    .padding(2.dp),
                text = time,
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
            Icon(
                painter = painterResource(icon),
                contentDescription = "status",
                tint = Color.Unspecified,
                modifier = Modifier
                    .width(60.dp)
                    .height(40.dp)
                    .padding(horizontal = 4.dp)
            )
            Text(
                modifier = Modifier
                    .padding(1.dp),
                text = "$temp°",
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
            Text(
                modifier = Modifier
                    .padding(1.dp),
                text = "\uD83D\uDCA7$rainChance%",
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
            Text(
                modifier = Modifier
                    .padding(1.dp),
                text = "$iconWind $windSpeed km/h",
                fontSize = MaterialTheme.typography.bodySmall.fontSize
            )
        }
    }
}

@Preview
@Composable
fun HourlyCardPreview(){
    HourlyCard()
}
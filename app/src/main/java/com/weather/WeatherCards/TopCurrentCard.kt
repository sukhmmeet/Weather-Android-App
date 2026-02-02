package com.weather.WeatherCards

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.TextUnitType
import androidx.compose.ui.unit.dp
import com.weather.ItemUI.CustomCard


@Composable
fun TopCurrentCard(

    day : String,
    time : String,
    temp : String,
    statusString : String,
    statusImg : Int,
    feelsLike : String,
    visibility: String
) {
    val celsiusSign = "°"

    CustomCard (){
        Column(
            modifier = Modifier
                .padding(all = 8.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ) {
                Text(
                    text = day,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
                Text(
                    text = time,
                    fontSize = MaterialTheme.typography.bodySmall.fontSize
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Transparent)
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween,
                verticalAlignment = Alignment.Bottom
            ) {
                Text(
                    modifier = Modifier
                        .padding(horizontal = 8.dp),
                    fontSize = TextUnit(value = 50f, type = TextUnitType.Sp),
                    fontWeight = FontWeight.SemiBold,
                    text = "$temp$celsiusSign"
                )
                Text(
                    fontWeight = FontWeight.SemiBold,
                    text = statusString
                )
                Icon(
                    painter = painterResource(statusImg),
                    contentDescription = "status",
                    tint = Color.Unspecified,
                    modifier = Modifier
                        .width(80.dp)
                        .height(60.dp)
                        .padding(horizontal = 8.dp)
                )
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(6.dp),
                horizontalArrangement = Arrangement.Absolute.SpaceBetween
            ){
                Text(
                    text = "$feelsLike$celsiusSign",
                    modifier = Modifier
                        .padding(all = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )
                Text(
                    text = "Visibility: $visibility km",
                    modifier = Modifier
                        .padding(all = 8.dp),
                    fontWeight = FontWeight.SemiBold
                )

            }
        }
    }
}
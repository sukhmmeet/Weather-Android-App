package com.weather.WeatherCards

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.ItemUI.DailyCard
import com.weather.OpenMetro.Daily

@Composable
fun DailyColumnCard(
    daily: Daily
){
    DailyCard(1, daily)
    DailyCard(2, daily)
    DailyCard(3, daily)
    DailyCard(4, daily)
    DailyCard(5, daily)
}

@Composable
@Preview
private fun Prev(){
    val daily = Daily(
        listOf(1.2),
        listOf(1.2),
        listOf(1.2),
        listOf(),
        listOf(1.2),
        listOf(),
        listOf(),
        listOf(1.2),
        listOf(1.2),
        listOf(""),
        listOf(1.2),
        listOf(1.2),
        listOf(1)
        ,listOf(1.2)
        ,listOf(1.2)
    )
    DailyColumnCard(daily)
}

package com.weather

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.weather.Geocoding.Forward.ForwardGeocoding
import com.weather.Geocoding.GeocodingForwardViewModel
import com.weather.Geocoding.UiStateForwardGeocoding
import com.weather.ItemUI.SearchItem
import com.weather.ui.theme.WeatherTheme

class SearchActivity : ComponentActivity() {

    private val geocodingForwardViewModel : GeocodingForwardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherTheme {
                Surface (modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                    .statusBarsPadding()) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        SearchLayout(geocodingForwardViewModel, context = LocalContext.current)
                    }
                }
            }
        }
    }
}

@Composable
fun SearchLayout(geocodingForwardViewModel: GeocodingForwardViewModel, context: Context){

    val state = geocodingForwardViewModel.state.value
    var searchValue by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp)
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = searchValue,
                onValueChange = {
                    searchValue = it
                    val query = it.trim()
                    if (query.length >= 3) {
                        geocodingForwardViewModel.fetchSuggestions(query)
                    }
                },
                label = { Text("Search") },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                }
            )
        }

        HorizontalDivider(
            modifier = Modifier
                .padding(vertical = 6.dp)
        )

        when (state) {
            is UiStateForwardGeocoding.Loading -> OnLoading()
            is UiStateForwardGeocoding.Error -> OnError(state.message)
            is UiStateForwardGeocoding.Success -> OnSuccess(
                state.suggestions,
                context = context
            )
        }

    }
}

@Composable
private fun OnLoading(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        CircularProgressIndicator()
    }
}

@Composable
private fun OnError(msg : String){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Text(
            text = msg,
            color = Color.Red,
            fontWeight = FontWeight.Bold,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize
        )
    }
}

@Composable
private fun OnSuccess(suggestion: List<ForwardGeocoding>, context: Context){
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(3.dp)
            .imePadding()
    ) {
        items(suggestion){ current ->
            SearchItem(
                title = current.name,
                state = current.address.state,
                country = current.address.country,
                onClick = {
                    val lon = current.lon
                    val lat = current.lat
                    val intent = Intent(context, HomeActivity::class.java)
                    intent.putExtra("longitude", lon)
                    intent.putExtra("latitude", lat)
                    intent.putExtra("address", current.name)
                    intent.putExtra("state", current.address.state)
                    intent.putExtra("country", current.address.country)
                    context.startActivity(intent)
                }
            )
            HorizontalDivider(
                thickness = 1.dp,
                color = Color.LightGray
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    WeatherTheme {

    }
}

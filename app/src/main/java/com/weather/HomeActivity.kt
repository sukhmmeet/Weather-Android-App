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
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.weather.ExtraFunctions.TimeUtils
import com.weather.ExtraFunctions.getCurrentHourIndex
import com.weather.ExtraFunctions.getWeatherIcon
import com.weather.ExtraFunctions.getWeatherStatus
import com.weather.ExtraFunctions.getWindDirectionIcon
import com.weather.Geocoding.Backward.BackwardGeocoding
import com.weather.Geocoding.GeocodingBackwardViewModel
import com.weather.Geocoding.UiStateReverseGeocoding
import com.weather.OpenMetro.UiStateWeatherData
import com.weather.OpenMetro.WeatherApiViewModel
import com.weather.OpenMetro.WeatherData
import com.weather.WeatherCards.DailyColumnCard
import com.weather.WeatherCards.HourlyRowCard
import com.weather.WeatherCards.SunAndRainCard
import com.weather.WeatherCards.TemperatureCard
import com.weather.WeatherCards.TopCurrentCard
import com.weather.WeatherCards.UVAndWindCard
import com.weather.ui.theme.WeatherTheme
import kotlinx.coroutines.launch
import kotlin.getValue

class HomeActivity : ComponentActivity() {
    private val weatherApiViewModel : WeatherApiViewModel by viewModels()
    private val geocodingBackwardViewModel : GeocodingBackwardViewModel by viewModels()
    private lateinit var lat : String
    private lateinit var lon : String
    private lateinit var address : String
    private lateinit var state : String
    private lateinit var country : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val systemDark = isSystemInDarkTheme()

            var isDarkTheme by remember {
                mutableStateOf(systemDark)
            }
            WeatherTheme(isDarkTheme) {
                Surface (modifier = Modifier
                    .fillMaxSize()
                    .background(if (isSystemInDarkTheme()) Color.Black else Color.White)
                    .statusBarsPadding()
                ) {
                    lon = intent.getStringExtra("longitude").toString()
                    lat = intent.getStringExtra("latitude").toString()
//
                    address = intent.getStringExtra("address") ?: "notAddress"
                    state = intent.getStringExtra("state") ?: ""
                    country = intent.getStringExtra("country") ?: ""
//
                    weatherApiViewModel.fetchWeatherData(lat,lon)

                    if(address == "notAddress"){
                        geocodingBackwardViewModel.fetchAddress(lat, lon)
                    }

                    RenderHomeLayout(
                        weatherApiViewModel,
                        geocodingBackwardViewModel,
                        address,
                        state,
                        country,
                        date = TimeUtils.getCurrentIndianDate(),
                        context = this@HomeActivity,
                        isDarkTheme = isDarkTheme,
                        onClickToggle = {
                            isDarkTheme = !isDarkTheme
                        }
                    )

//                    HomeActivityScreen(
//                        topBarAddress = address,
//                        date = TimeUtils.getCurrentIndianDate(),
//                        context = this@HomeActivity
//                    )
                }
            }
        }
    }
}

@Composable
fun RenderHomeLayout(
    weatherApiViewModel: WeatherApiViewModel,
    geocodingBackwardViewModel: GeocodingBackwardViewModel,
    address: String,
    stateName: String,
    country: String,
    date: String,
    context: HomeActivity,
    onClickToggle: () -> Unit,
    isDarkTheme: Boolean
) {
    val state = weatherApiViewModel.state.value
    when(state){
        is UiStateWeatherData.OnSuccess -> HomeActivityScreen(
            topBarAddress = address,
            date = date,
            context = context,
            geocodingBackwardViewModel,
            state.weatherData,
            stateName = stateName,
            country = country,
            onClickToggle,
            isDarkTheme
        )
        is UiStateWeatherData.Loading -> OnLoadingInRender()
        is UiStateWeatherData.OnError -> OnErrorInRender(state.message)
    }
}

@Composable
fun OnErrorInRender(msg : String){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = msg,
            fontSize = MaterialTheme.typography.headlineMedium.fontSize,
            color = Color.Red
        )
    }
}

@Composable
fun OnLoadingInRender(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = "Please wait... ",
                fontSize = MaterialTheme.typography.bodyMedium.fontSize
            )
            CircularProgressIndicator(
                modifier = Modifier.size(20.dp)
            )
        }
    }
}


@Composable
fun HomeActivityScreen(
    topBarAddress: String,
    date: String,
    context: Context,
    geocodingBackwardViewModel: GeocodingBackwardViewModel,
    weatherData: WeatherData,
    stateName: String,
    country: String,
    onClickToggle: () -> Unit,
    isDarkTheme: Boolean
) {
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            Row(
                modifier = Modifier
                    .padding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 16.dp),
                    onClick = {
                        scope.launch {
                            drawerState.open()
                        }
                    }
                ) {
                    Icon(imageVector = Icons.Default.Menu, contentDescription = "menuIcon")
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                Column(
                    Modifier
                        .padding(all = 8.dp)
                        .weight(8f),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {

                    if(topBarAddress != "notAddress"){
                        Text(
                            text = topBarAddress,
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis,
                            fontWeight = FontWeight.Bold,
                            fontSize = MaterialTheme.typography.titleLarge.fontSize
                        )
                    }else{
                        val state = geocodingBackwardViewModel.state.value
                        when(state){
                            is UiStateReverseGeocoding.Loading -> AddressLoading()
                            is UiStateReverseGeocoding.onError -> AddressError(state.message)
                            is UiStateReverseGeocoding.onSuccess -> AddressSuccess(state.address)
                        }
                    }
                    Text(
                        text = date,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = MaterialTheme.typography.titleSmall.fontSize
                    )
                }
                Spacer(modifier = Modifier.padding(horizontal = 5.dp))
                IconButton(
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 16.dp),
                    onClick = {
                        val intent = Intent(context, SearchActivity::class.java)
                        context.startActivity(intent)
                    }
                ) {
                    Icon(imageVector = Icons.Default.Search, contentDescription = "searchIcon")
                }
            }
        }
    ) { innerPadding ->
        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    if (topBarAddress != "notAddress") {
                        NavigationDrawerContent(
                            address = topBarAddress,
                            state = stateName,
                            country = country,
                            innerPadding = innerPadding,
                            onClickToggle = onClickToggle,
                            isDarkTheme
                        )
                    } else {
                        val state = geocodingBackwardViewModel.state.value
                        when (state) {
                            is UiStateReverseGeocoding.Loading -> AddressLoading()
                            is UiStateReverseGeocoding.onError ->
                                AddressError(state.message)

                            is UiStateReverseGeocoding.onSuccess -> {
                                NavigationDrawerContent(
                                    address = state.address.display_name,
                                    state = state.address.address.state,
                                    country = state.address.address.country,
                                    innerPadding = innerPadding,
                                    onClickToggle,
                                    isDarkTheme
                                )
                            }
                        }
                    }
                }
            },
            drawerState = drawerState,
            modifier = Modifier
        ) {
            HomeScreenContent(innerPadding, weatherData)
        }
    }
}

@Composable
fun NavigationDrawerContent(
    address: String,
    state: String,
    country: String,
    innerPadding: PaddingValues,
    onClickToggle: () -> Unit,
    isDarkTheme: Boolean
){

    Column(
        modifier = Modifier
            .padding(innerPadding)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "${state},",
            modifier = Modifier
                .padding(horizontal = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = country,
            modifier = Modifier
                .padding(start = 16.dp, end = 16.dp, bottom = 16.dp),
            style = MaterialTheme.typography.titleMedium,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        HorizontalDivider()
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Dark Mode  ",
                fontSize = MaterialTheme.typography.titleLarge.fontSize
            )
            Switch(
                checked = isDarkTheme,
                onCheckedChange = {
                    onClickToggle()
                }
            )
        }
    }
}

@Composable
fun HomeScreenContent(innerPadding: PaddingValues, weatherData: WeatherData){
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .padding(innerPadding)
    ) {
        var currentHour = getCurrentHourIndex(weatherData.hourly)
        if(currentHour > 0) {
            currentHour -= 1
        }
        TopCurrentCard(
            day = TimeUtils.getTodayDay(),
            time = TimeUtils.getCurrentIndianTime(),
            temp = weatherData.current.temperature_2m.toString(),
            statusString = getWeatherStatus(
                temperature = weatherData.current.temperature_2m,
                apparentTemperature = weatherData.current.apparent_temperature,
                isDay = weatherData.current.is_day,
                windSpeed = weatherData.current.wind_speed_10m,
                cloudCover = weatherData.current.cloud_cover,
                precipitation = weatherData.current.precipitation
            ),
            statusImg = getWeatherIcon(weatherData.current.weather_code, weatherData.current.is_day),
            feelsLike = "Feels like ${weatherData.current.apparent_temperature}",
            visibility = (weatherData.hourly.visibility[currentHour]/1000).toString()
        )
        HourlyRowCard(
            weatherData.hourly
        )
        TemperatureCard(
            min = weatherData.daily.temperature_2m_min[0].toString(),
            max = weatherData.daily.temperature_2m_max[0].toString(),
            feelMin = weatherData.daily.apparent_temperature_min[0].toString(),
            feelMax = weatherData.daily.apparent_temperature_max[0].toString()
        )
        SunAndRainCard(
            sunrise = TimeUtils.isoToIndian12Hr(weatherData.daily.sunrise[0]),
            sunset = TimeUtils.isoToIndian12Hr(weatherData.daily.sunset[0]),
            daylight = TimeUtils.secondsToHourMinute(weatherData.daily.daylight_duration[0].toInt()),
            chanceRain = weatherData.hourly.precipitation_probability[currentHour].toString(),
            maxPrecipitation = weatherData.hourly.precipitation[currentHour].toString()
        )
        UVAndWindCard(
            maxUV = weatherData.daily.uv_index_max[0].toString(),
            clearSkyUV = weatherData.daily.uv_index_clear_sky_max[0].toString(),
            speed = weatherData.current.wind_speed_10m.toString(),
            gusts = weatherData.daily.wind_gusts_10m_max[0].toString(),
            windDirectionIcon = getWindDirectionIcon(weatherData.current.wind_direction_10m)
        )
        DailyColumnCard(
            weatherData.daily
        )
    }
}

@Composable
fun AddressSuccess(backwardGeocoding: BackwardGeocoding){
    Text(
        text = backwardGeocoding.display_name,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
}

@Composable
fun AddressError(msg : String){
    Text(
        text = msg,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
}

@Composable
fun AddressLoading(){
    Text(
        text = "Loading Address...",
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        fontWeight = FontWeight.Bold,
        fontSize = MaterialTheme.typography.titleLarge.fontSize
    )
}


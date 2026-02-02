package com.weather.OpenMetro

import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

sealed class UiStateWeatherData{
    object Loading : UiStateWeatherData()
    data class OnSuccess(val weatherData : WeatherData) : UiStateWeatherData()
    data class OnError(val message : String) : UiStateWeatherData()
}
class WeatherApiViewModel : ViewModel() {
    private val repository = WeatherRepository()

    private val internalState : MutableState<UiStateWeatherData> = mutableStateOf(UiStateWeatherData.Loading)

    val state : State<UiStateWeatherData> = internalState

    fun fetchWeatherData(latitude: String, longitude: String){
        viewModelScope.launch {
            internalState.value = UiStateWeatherData.Loading
            try {
                val result = repository.getWeatherData(lat = latitude, lon = longitude)

                Log.d("API_TEST", "API Success: $result")
                internalState.value = UiStateWeatherData.OnSuccess(result)
            }catch (e : Exception){
                Log.e("API_TEST", "API Error: ${e.message}")
                internalState.value = UiStateWeatherData.OnError(e.toString())
            }
        }
    }

}
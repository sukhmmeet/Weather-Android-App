package com.weather.Geocoding


import android.util.Log
import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.ViewModel
import com.weather.Geocoding.Backward.BackwardGeocoding
import kotlinx.coroutines.launch


sealed class UiStateReverseGeocoding{
    object Loading : UiStateReverseGeocoding()
    data class onSuccess(val address: BackwardGeocoding) : UiStateReverseGeocoding()
    data class onError(val message : String) : UiStateReverseGeocoding()
}
class GeocodingBackwardViewModel : ViewModel() {
    private val repository = GeocodingRepository()

    private val internalState : MutableState<UiStateReverseGeocoding> = mutableStateOf(
        UiStateReverseGeocoding.Loading)

    val state : State<UiStateReverseGeocoding> = internalState

    fun fetchAddress(latitude: String, longitude: String){
        viewModelScope.launch {
            internalState.value = UiStateReverseGeocoding.Loading
            try {
                val result = repository.getAddress(latitude = latitude, longitude = longitude)
                Log.d("API_TEST", "API Success: $result")
                internalState.value = UiStateReverseGeocoding.onSuccess(result)
            } catch (e: Exception){
                Log.e("API_TEST", "API Error: ${e.message}")
                internalState.value = UiStateReverseGeocoding.onError(e.message ?: "Unknown Error")
            }
        }
    }

//    fun fetchAddress(latitude : String, longitude : String){
//        viewModelScope.launch {
//            internalState.value = UiStateReverseGeocoding.Loading
//            try {
//                val result = repository.getAddress(latitude = latitude, longitude = longitude)
//                internalState.value = UiStateReverseGeocoding.onSuccess(result)
//            } catch (e : Exception){
//                internalState.value = UiStateReverseGeocoding.onError(e.toString())
//            }
//        }
//    }

}
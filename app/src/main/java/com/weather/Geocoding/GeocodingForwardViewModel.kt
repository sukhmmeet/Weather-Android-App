package com.weather.Geocoding

import androidx.compose.runtime.State
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.Geocoding.Forward.ForwardGeocoding
import kotlinx.coroutines.launch


sealed class UiStateForwardGeocoding{
    object Loading : UiStateForwardGeocoding()
    data class Success(val suggestions : List<ForwardGeocoding>) : UiStateForwardGeocoding()
    data class Error(val message : String) : UiStateForwardGeocoding()
}
class GeocodingForwardViewModel : ViewModel() {

    private val repository = GeocodingRepository()

    private var internalState: MutableState<UiStateForwardGeocoding> =
        mutableStateOf(UiStateForwardGeocoding.Loading)

    val state: State<UiStateForwardGeocoding> = internalState

    fun fetchSuggestions(search: String) {
        if (search.length < 3) return

        viewModelScope.launch {
            internalState.value = UiStateForwardGeocoding.Loading
            try {
                val result = repository.getSuggestion(search)
                internalState.value = UiStateForwardGeocoding.Success(result)
            } catch (e: Exception) {
                internalState.value = UiStateForwardGeocoding.Error(e.message ?: "Unknown error")
            }
        }
    }
}
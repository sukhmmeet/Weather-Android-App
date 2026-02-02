package com.weather.Geocoding

import android.util.Log
import com.weather.Geocoding.Backward.BackwardGeocoding
import com.weather.Geocoding.Backward.GeocodingBackwardObject
import com.weather.Geocoding.Forward.ForwardGeocoding
import com.weather.Geocoding.Forward.GeocodingForwardApiInterface
import com.weather.Geocoding.Forward.GeocodingForwardApiObject

class GeocodingRepository {
    suspend fun getSuggestion(search : String) : List<ForwardGeocoding> {
        return GeocodingForwardApiObject.api.getSuggestions(search = search)
    }
    suspend fun getAddress(latitude : String, longitude : String) : BackwardGeocoding {
        return GeocodingBackwardObject.api.getAddress(latitude, longitude)
    }
}
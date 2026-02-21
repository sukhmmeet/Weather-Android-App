package com.weather.Geocoding.Backward

import com.weather.API_KEYS
import retrofit2.http.GET
import retrofit2.http.Query

//https://geocode.maps.co/reverse?lat=latitude&lon=longitude&api_key=YOUR_SECRET_API_KEY

interface GeocodingBackwardApiInterface {
    @GET("reverse")
    suspend fun getAddress(
        @Query("lat") latitude : String,
        @Query("lon") longitude : String,
        @Query("api_key") apiKey : String = API_KEYS.GEOCODING_API_KEY
    ) : BackwardGeocoding
}
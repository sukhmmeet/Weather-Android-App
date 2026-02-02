package com.weather.Geocoding.Forward

import retrofit2.http.GET
import retrofit2.http.Query

// https://geocode.maps.co/search?q=address&api_key=YOUR_SECRET_API_KEY

interface GeocodingForwardApiInterface {
    @GET("search")
    suspend fun getSuggestions(
        @Query("q") search: String,
        @Query("api_key") apiKey: String = "69778063dfba5384447009lcd7f7213"
    ): List<ForwardGeocoding>
}
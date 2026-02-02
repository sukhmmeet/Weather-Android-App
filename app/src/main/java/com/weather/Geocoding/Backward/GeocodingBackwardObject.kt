package com.weather.Geocoding.Backward

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingBackwardObject {
    private const val baseUrl = "https://geocode.maps.co/"
    val api : GeocodingBackwardApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingBackwardApiInterface::class.java)
    }
}
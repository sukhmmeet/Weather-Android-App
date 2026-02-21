package com.weather.Geocoding.Forward

import com.weather.ApiConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object GeocodingForwardApiObject {
    private const val BASE_URL = ApiConfig.GEOCODING_BASE_URL

    val api : GeocodingForwardApiInterface by lazy {
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GeocodingForwardApiInterface::class.java)
    }
}
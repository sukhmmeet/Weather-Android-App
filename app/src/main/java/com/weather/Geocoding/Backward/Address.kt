package com.weather.Geocoding.Backward

import com.google.gson.annotations.SerializedName

data class Address(
    @SerializedName("ISO3166-2-lvl4")
    val iso31662lvl4: String,
    val country: String,
    @SerializedName("country_code")
    val countryCode: String,
    val county: String,
    val postcode: String,
    val state: String,
    @SerializedName("state_district")
    val stateDistrict: String,
    val village: String
)
package com.weather.Geocoding.Forward

data class ForwardGeocoding(
    val address: Address,
    val addresstype: String,
    val boundingbox: List<String>,
    val `class`: String,
    val display_name: String,
    val extratags: Extratags,
    val importance: Double,
    val lat: String,
    val licence: String,
    val lon: String,
    val name: String,
    val namedetails: Namedetails,
    val osm_id: Long,
    val osm_type: String,
    val place_id: Int,
    val place_rank: Int,
    val type: String
)
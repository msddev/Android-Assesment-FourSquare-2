package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("address")
    val address: String? = "",
    @SerializedName("lat")
    val lat: Double? = 0.0,
    @SerializedName("lng")
    val lng: Double? = 0.0,
    @SerializedName("distance")
    val distance: Int? = 0,
    @SerializedName("formattedAddress")
    val formattedAddress: List<String>? = listOf()
)
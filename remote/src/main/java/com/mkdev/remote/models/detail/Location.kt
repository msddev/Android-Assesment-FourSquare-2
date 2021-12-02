package com.mkdev.remote.models.detail


import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("lat")
    val lat: Double = 0.0,
    @SerializedName("lng")
    val lng: Double = 0.0,
    @SerializedName("cc")
    val cc: String = "",
    @SerializedName("country")
    val country: String = "",
    @SerializedName("formattedAddress")
    val formattedAddress: List<String> = listOf()
)
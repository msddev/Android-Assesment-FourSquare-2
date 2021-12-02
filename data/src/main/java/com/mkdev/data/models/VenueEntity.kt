package com.mkdev.data.models

data class VenueEntity(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String? = "",
    val distance: Int,
    val categoryType: String,
    val categoryIcon: String,
    val picture: String,
    val phone: String,
    val likes: Int,
    var userCurrentLatLng: String
)
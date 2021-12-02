package com.mkdev.data.models

data class VenueDetailEntity(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String,
    val categoryType: String,
    val categoryIcon: String,
    val picture: String,
    val likes: Int,
    val phone: String
)
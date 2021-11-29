package com.mkdev.data.entity

data class VenueEntity(
    val id: String,
    val name: String,
    val latitude: Double,
    val longitude: Double,
    val address: String ?= "",
    val distance: Int,
    val categoryType: String,
    val categoryIcon: String,
    val picture: String
)
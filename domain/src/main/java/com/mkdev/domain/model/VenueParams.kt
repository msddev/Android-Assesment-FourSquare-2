package com.mkdev.domain.model

data class VenueParams(
    val latLng: String,
    val limit: Int,
    val offset: Int
)
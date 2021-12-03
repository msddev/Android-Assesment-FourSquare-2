package com.mkdev.presentation.locationProviders

interface LocationProvider {
    suspend fun isLocationChanged(lastLat: Double, lastLng: Double):Boolean
    suspend fun getLocationLatLng(): String
}
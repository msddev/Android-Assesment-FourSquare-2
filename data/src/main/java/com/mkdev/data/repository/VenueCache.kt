package com.mkdev.data.repository

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity

interface VenueCache {
    suspend fun getVenues(latLng: String): List<VenueEntity>
    suspend fun getVenueDetailById(id: String): VenueDetailEntity

    suspend fun saveVenues(listVenues: List<VenueEntity>)
    suspend fun deleteAllVenues()
    suspend fun getLatestLocations(): List<String>
    suspend fun isCached(): Boolean
}

package com.mkdev.data.repository

import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity

interface VenueDataSource {
    // Remote and cache
    suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity>
    suspend fun getVenueDetailById(id: String): VenueEntity

    // Cache
    suspend fun saveVenues(listVenues: List<VenueEntity>)
    suspend fun saveVenue(venue: VenueEntity)
    suspend fun deleteAllVenues()
    suspend fun getLatestLocations(): List<String>
    suspend fun isCached(): Boolean
    suspend fun isCached(latLng: String): Boolean
    suspend fun isCached(params: VenueParamsEntity): Boolean
}

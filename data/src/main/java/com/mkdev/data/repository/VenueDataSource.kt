package com.mkdev.data.repository

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity

interface VenueDataSource {
    // Remote and cache
    suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity>
    suspend fun getVenueById(id: String): VenueDetailEntity

    // Cache
    suspend fun saveVenues(listVenues: List<VenueEntity>)
    suspend fun deleteAllVenues()
    suspend fun getLatestLocations(): List<String>
    suspend fun isCached(): Boolean
}

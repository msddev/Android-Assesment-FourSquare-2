package com.mkdev.data.repository

import com.mkdev.data.entity.VenueDetailEntity
import com.mkdev.data.entity.VenueEntity

interface VenueCache {
    suspend fun getVenues(latLng: String): List<VenueEntity>
    suspend fun getVenueById(id: String): VenueDetailEntity
    suspend fun saveVenues(listVenues: List<VenueEntity>)
    suspend fun saveVenueDetail(detail: VenueDetailEntity)
    suspend fun isCached(): Boolean
}

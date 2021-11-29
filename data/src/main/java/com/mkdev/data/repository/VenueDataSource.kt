package com.mkdev.data.repository

import com.mkdev.data.entity.VenueDetailEntity
import com.mkdev.data.entity.VenueEntity
import com.mkdev.data.entity.VenueParamsEntity

interface VenueDataSource {
    // Remote and cache
    suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity>
    suspend fun getVenueById(id: String): VenueDetailEntity

    // Cache
    suspend fun saveVenues(listVenues: List<VenueEntity>)
    suspend fun saveVenueDetail(detail: VenueDetailEntity)
    suspend fun isCached(): Boolean
}

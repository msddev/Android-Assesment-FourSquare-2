package com.mkdev.domain.repository

import com.mkdev.domain.model.VenueDetail
import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import kotlinx.coroutines.flow.Flow


interface VenueRepository {

    // Remote and cache
    fun getNearVenues(param: VenueParams): Flow<List<Venue>>
    fun getVenueById(id: String): Flow<VenueDetail>

    // Cache
    suspend fun saveNearVenues(listNearVenues: List<Venue>)
}
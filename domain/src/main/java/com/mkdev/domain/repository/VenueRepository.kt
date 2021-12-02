package com.mkdev.domain.repository

import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import kotlinx.coroutines.flow.Flow

interface VenueRepository {
    // Remote and cache
    fun getNearVenues(param: VenueParams): Flow<List<Venue>>
    fun getVenueDetailById(id: String): Flow<Venue>

    // Cache
    suspend fun saveNearVenues(listNearVenues: List<Venue>, userCurrentLatLng: String)
    suspend fun saveVenueDetail(venue: Venue)
}
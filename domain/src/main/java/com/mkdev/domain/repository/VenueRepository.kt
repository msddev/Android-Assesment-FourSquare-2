package com.mkdev.domain.repository

import com.mkdev.domain.entity.VenueDetailEntity
import com.mkdev.domain.entity.VenueEntity
import com.mkdev.domain.entity.VenueParams
import kotlinx.coroutines.flow.Flow


interface VenueRepository {

    fun getNearVenues(param: VenueParams): Flow<List<VenueEntity>>

    fun getVenueById(id: String): Flow<VenueDetailEntity>
}
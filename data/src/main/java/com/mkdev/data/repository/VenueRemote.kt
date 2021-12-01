package com.mkdev.data.repository

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity

interface VenueRemote {
    suspend fun getNearVenues(params: VenueParamsEntity): List<VenueEntity>
    suspend fun getVenueDetailById(id: String): VenueDetailEntity
}

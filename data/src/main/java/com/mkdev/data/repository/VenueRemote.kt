package com.mkdev.data.repository

import com.mkdev.data.entity.VenueDetailEntity
import com.mkdev.data.entity.VenueEntity
import com.mkdev.data.entity.VenueParamsEntity

interface VenueRemote {
    suspend fun getNearVenues(params: VenueParamsEntity): List<VenueEntity>
    suspend fun getVenueById(id: String): VenueDetailEntity
}

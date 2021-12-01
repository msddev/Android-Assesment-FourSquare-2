package com.mkdev.data.source

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity
import com.mkdev.data.repository.VenueCache
import com.mkdev.data.repository.VenueDataSource
import javax.inject.Inject

class VenueCacheDataSource @Inject constructor(
    private val cache: VenueCache
) : VenueDataSource {
    override suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity> =
        cache.getVenues(params.latLng)

    override suspend fun getVenueById(id: String): VenueDetailEntity =
        cache.getVenueById(id)

    override suspend fun saveVenues(listVenues: List<VenueEntity>) =
        cache.saveVenues(listVenues)

    override suspend fun deleteAllVenues() =
        cache.deleteAllVenues()

    override suspend fun getLatestLocations(): List<String> =
        cache.getLatestLocations()

    override suspend fun isCached(): Boolean =
        cache.isCached()
}
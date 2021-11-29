package com.mkdev.data.source

import com.mkdev.data.entity.VenueDetailEntity
import com.mkdev.data.entity.VenueEntity
import com.mkdev.data.entity.VenueParamsEntity
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

    override suspend fun saveVenueDetail(detail: VenueDetailEntity) =
        cache.saveVenueDetail(detail)

    override suspend fun isCached(): Boolean =
        cache.isCached()
}
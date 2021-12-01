package com.mkdev.cache.datasourceImpl

import com.mkdev.cache.dao.VenueDao
import com.mkdev.cache.mapper.VenueCacheMapper
import com.mkdev.cache.mapper.VenueDetailCacheMapper
import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.repository.VenueCache
import javax.inject.Inject

class VenueCacheImpl @Inject constructor(
    private val venueDao: VenueDao,
    private val venueCacheMapper: VenueCacheMapper,
    private val venueDetailCacheMapper: VenueDetailCacheMapper,
) : VenueCache {
    override suspend fun getVenues(latLng: String): List<VenueEntity> =
        venueDao.getVenues(latLng).map { cacheVenue ->
            venueCacheMapper.mapFromCached(cacheVenue)
        }

    override suspend fun getVenueById(id: String): VenueDetailEntity =
        venueDetailCacheMapper.mapFromCached(venueDao.getVenueById(id))

    override suspend fun saveVenues(listVenues: List<VenueEntity>) {
        listVenues.map {
            venueDao.insertVenue(venueCacheMapper.mapToCached(it))
        }
    }

    override suspend fun deleteAllVenues() =
        venueDao.deleteAllVenues()

    override suspend fun getLatestLocations(): List<String> =
        venueDao.getLatestLocations()

    override suspend fun isCached(): Boolean =
        venueDao.getVenues().isNotEmpty()
}
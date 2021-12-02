package com.mkdev.cache.datasourceImpl

import com.mkdev.cache.dao.VenueDao
import com.mkdev.cache.mapper.VenueCacheMapper
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity
import com.mkdev.data.repository.VenueCache
import javax.inject.Inject

class VenueCacheImpl @Inject constructor(
    private val venueDao: VenueDao,
    private val venueCacheMapper: VenueCacheMapper,
) : VenueCache {
    override suspend fun getVenues(latLng: String): List<VenueEntity> =
        venueDao.getVenues(latLng).map { cacheVenue ->
            venueCacheMapper.mapFromCached(cacheVenue)
        }
    override suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity> =
        venueDao.getVenuesPaging(params.latLng, params.limit, params.offset).map { cacheVenue ->
            venueCacheMapper.mapFromCached(cacheVenue)
        }

    override suspend fun getVenueDetailById(id: String): VenueEntity =
        venueCacheMapper.mapFromCached(venueDao.getVenueById(id))

    override suspend fun saveVenues(listVenues: List<VenueEntity>) {
        listVenues.map {
            venueDao.insertVenue(venueCacheMapper.mapToCached(it))
        }
    }

    override suspend fun saveVenue(venue: VenueEntity) =
        venueCacheMapper.mapToCached(venue).let {
            venueDao.updateVenue(it.id, it.picture, it.phone)
        }

    override suspend fun deleteAllVenues() =
        venueDao.deleteAllVenues()

    override suspend fun getLatestLocations(): List<String> =
        venueDao.getLatestLocations()

    override suspend fun isCached(): Boolean =
        venueDao.getVenues().isNotEmpty()

    override suspend fun isCached(latLng: String): Boolean =
        venueDao.getVenues(latLng).isNotEmpty()

    override suspend fun isCached(params: VenueParamsEntity): Boolean =
        venueDao.getVenuesPaging(params.latLng, params.limit, params.offset).isNotEmpty()
}
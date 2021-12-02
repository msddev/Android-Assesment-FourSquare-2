package com.mkdev.data.source

import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity
import com.mkdev.data.repository.VenueDataSource
import com.mkdev.data.repository.VenueRemote
import javax.inject.Inject

class VenueRemoteDataSource @Inject constructor(
    private val remote: VenueRemote
) : VenueDataSource {
    override suspend fun getVenues(params: VenueParamsEntity): List<VenueEntity> =
        remote.getNearVenues(params)

    override suspend fun getVenueDetailById(id: String): VenueEntity =
        remote.getVenueDetailById(id)

    override suspend fun saveVenues(listVenues: List<VenueEntity>) {
        throw UnsupportedOperationException("Save operation is not supported for RemoteDataSource.")
    }

    override suspend fun saveVenue(venue: VenueEntity) {
        throw UnsupportedOperationException("Save operation is not supported for RemoteDataSource.")
    }

    override suspend fun deleteAllVenues() {
        throw UnsupportedOperationException("Delete operation is not supported for RemoteDataSource.")
    }

    override suspend fun getLatestLocations(): List<String> {
        throw UnsupportedOperationException("Get location operation is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(): Boolean {
        throw UnsupportedOperationException("Check cache is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(latLng: String): Boolean {
        throw UnsupportedOperationException("Check cache is not supported for RemoteDataSource.")
    }

    override suspend fun isCached(params: VenueParamsEntity): Boolean {
        throw UnsupportedOperationException("Check cache is not supported for RemoteDataSource.")
    }
}
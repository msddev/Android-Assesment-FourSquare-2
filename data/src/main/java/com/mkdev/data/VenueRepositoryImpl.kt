package com.mkdev.data

import com.mkdev.domain.model.VenueDetail
import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(

) : VenueRepository {
    override fun getNearVenues(param: VenueParams): Flow<List<Venue>> {
        TODO("Not yet implemented")
    }

    override fun getVenueById(id: String): Flow<VenueDetail> {
        TODO("Not yet implemented")
    }

    override suspend fun saveNearVenues(listNearVenues: List<Venue>) {
        TODO("Not yet implemented")
    }
}
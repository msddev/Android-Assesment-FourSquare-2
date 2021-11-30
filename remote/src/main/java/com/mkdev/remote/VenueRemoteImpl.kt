package com.mkdev.remote

import com.mkdev.data.entity.VenueDetailEntity
import com.mkdev.data.entity.VenueEntity
import com.mkdev.data.entity.VenueParamsEntity
import com.mkdev.data.repository.VenueRemote
import com.mkdev.remote.api.FourSquareService
import com.mkdev.remote.mappers.NearVenueEntityMapper
import com.mkdev.remote.mappers.VenueEntityMapper
import javax.inject.Inject

class VenueRemoteImpl @Inject constructor(
    private val service: FourSquareService,
    private val nearVenueEntityMapper: NearVenueEntityMapper
) : VenueRemote {
    override suspend fun getNearVenues(params: VenueParamsEntity): List<VenueEntity> =
        service.getNearVenues(params.latLng, params.limit, params.offset)
            .response.groups[0].items.map { nearVenueEntityMapper.mapFromResponse(it) }

    override suspend fun getVenueById(id: String): VenueDetailEntity =
        VenueEntityMapper().mapFromResponse(service.getVenueById(id).response.venue)


}
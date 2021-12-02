package com.mkdev.remote

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.data.models.VenueEntity
import com.mkdev.data.models.VenueParamsEntity
import com.mkdev.data.repository.VenueRemote
import com.mkdev.remote.api.FourSquareService
import com.mkdev.remote.mappers.NearVenueEntityMapper
import com.mkdev.remote.mappers.VenueDetailEntityMapper
import com.mkdev.remote.models.detail.VenueDetail
import javax.inject.Inject

class VenueRemoteImpl @Inject constructor(
    private val service: FourSquareService,
    private val nearVenueEntityMapper: NearVenueEntityMapper
) : VenueRemote {
    override suspend fun getNearVenues(params: VenueParamsEntity): List<VenueEntity> =
        service.getNearVenues(
            params.latLng,
            params.limit,
            params.offset
        ).data?.let { data ->
            data.groups[0].items.map { nearVenueEntityMapper.mapFromResponse(it) }
        } ?: run {
            listOf()
        }

    override suspend fun getVenueDetailById(id: String): VenueDetailEntity =
        VenueDetailEntityMapper().mapFromResponse(service.getVenueById(id).data?.venueDetail ?: VenueDetail())


}
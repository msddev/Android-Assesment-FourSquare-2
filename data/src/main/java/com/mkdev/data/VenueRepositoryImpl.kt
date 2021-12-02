package com.mkdev.data

import com.mkdev.data.mapper.VenueBodyMapper
import com.mkdev.data.mapper.VenueMapper
import com.mkdev.data.source.VenueDataSourceFactory
import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class VenueRepositoryImpl @Inject constructor(
    private val dataSourceFactory: VenueDataSourceFactory,
    private val venueMapper: VenueMapper
) : VenueRepository {
    override fun getNearVenues(param: VenueParams): Flow<List<Venue>> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        val venuesList =
            dataSourceFactory.getDataStore(isCached).getVenues(VenueBodyMapper().mapToEntity(param))
                .map { venueEntity ->
                    venueEntity.userCurrentLatLng = param.latLng
                    venueMapper.mapFromEntity(venueEntity)
                }
        saveNearVenues(venuesList, param.latLng)
        emit(venuesList)
    }

    override fun getVenueDetailById(id: String): Flow<Venue> = flow {
        val isCached = dataSourceFactory.getCacheDataSource().isCached()
        val venuesDetailEntity =
            dataSourceFactory.getDataStore(isCached).getVenueDetailById(id)

        emit(venueMapper.mapFromEntity(venuesDetailEntity))
    }

    override suspend fun saveNearVenues(listNearVenues: List<Venue>, userCurrentLatLng: String) {
        val characterEntities = listNearVenues.map { venue ->
            venue.userCurrentLatLng = userCurrentLatLng
            venueMapper.mapToEntity(venue)
        }
        dataSourceFactory.getCacheDataSource()
            .saveVenues(characterEntities)
    }
}
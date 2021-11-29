package com.mkdev.domain.intractor

import com.mkdev.domain.model.Venue
import com.mkdev.domain.model.VenueParams
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNearVenuesUseCase @Inject constructor(
    private val repository: VenueRepository
) : BaseUseCase<VenueParams, Flow<List<Venue>>> {
    override suspend fun invoke(params: VenueParams): Flow<List<Venue>> =
        repository.getNearVenues(params)
}
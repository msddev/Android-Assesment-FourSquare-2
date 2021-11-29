package com.mkdev.domain.intractor

import com.mkdev.domain.entity.VenueEntity
import com.mkdev.domain.entity.VenueParams
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNearVenuesUseCase @Inject constructor(
    private val repository: VenueRepository
) : BaseUseCase<VenueParams, Flow<List<VenueEntity>>> {
    override suspend fun invoke(params: VenueParams): Flow<List<VenueEntity>> =
        repository.getNearVenues(params)
}
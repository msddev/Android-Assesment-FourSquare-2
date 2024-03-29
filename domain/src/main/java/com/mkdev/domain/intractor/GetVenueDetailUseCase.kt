package com.mkdev.domain.intractor

import com.mkdev.domain.model.Venue
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVenueDetailUseCase @Inject constructor(
    private val repository: VenueRepository
) : BaseUseCase<String, Flow<Venue>> {
    override suspend fun invoke(params: String): Flow<Venue> =
        repository.getVenueDetailById(params)
}
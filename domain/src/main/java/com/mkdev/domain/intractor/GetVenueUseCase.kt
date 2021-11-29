package com.mkdev.domain.intractor

import com.mkdev.domain.entity.VenueDetailEntity
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVenueByIdUseCase @Inject constructor(
    private val repository: VenueRepository
) : BaseUseCase<String, Flow<VenueDetailEntity>> {
    override suspend fun invoke(params: String): Flow<VenueDetailEntity> =
        repository.getVenueById(params)
}
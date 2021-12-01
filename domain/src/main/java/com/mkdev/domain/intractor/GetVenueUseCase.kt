package com.mkdev.domain.intractor

import com.mkdev.domain.model.VenueDetail
import com.mkdev.domain.repository.VenueRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetVenueByIdUseCase @Inject constructor(
    private val repository: VenueRepository
) : BaseUseCase<String, Flow<VenueDetail>> {
    override suspend fun invoke(params: String): Flow<VenueDetail> =
        repository.getVenueDetailById(params)
}
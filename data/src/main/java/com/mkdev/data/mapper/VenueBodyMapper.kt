package com.mkdev.data.mapper

import com.mkdev.data.models.VenueParamsEntity
import com.mkdev.domain.model.VenueParams
import javax.inject.Inject

class VenueBodyMapper @Inject constructor() : Mapper<VenueParamsEntity, VenueParams> {
    override fun mapFromEntity(type: VenueParamsEntity): VenueParams =
        VenueParams(
            latLng = type.latLng,
            limit = type.limit,
            offset = type.offset
        )

    override fun mapToEntity(type: VenueParams): VenueParamsEntity =
        VenueParamsEntity(
            latLng = type.latLng,
            limit = type.limit,
            offset = type.offset
        )
}

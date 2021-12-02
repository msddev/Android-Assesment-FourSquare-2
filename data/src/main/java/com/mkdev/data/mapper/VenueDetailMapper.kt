package com.mkdev.data.mapper

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.domain.model.VenueDetail
import javax.inject.Inject

class VenueDetailMapper @Inject constructor() : Mapper<VenueDetailEntity, VenueDetail> {
    override fun mapFromEntity(type: VenueDetailEntity): VenueDetail =
        VenueDetail(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            likes = type.likes,
            phone = type.phone
        )

    override fun mapToEntity(type: VenueDetail): VenueDetailEntity =
        VenueDetailEntity(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            likes = type.likes,
            phone = type.phone
        )
}

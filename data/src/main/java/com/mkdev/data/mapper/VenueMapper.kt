package com.mkdev.data.mapper

import com.mkdev.data.models.VenueEntity
import com.mkdev.domain.model.Venue
import javax.inject.Inject

class VenueMapper @Inject constructor() : Mapper<VenueEntity, Venue> {
    override fun mapFromEntity(type: VenueEntity): Venue =
        Venue(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            distance = type.distance,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            userCurrentLatLng = type.userCurrentLatLng
        )

    override fun mapToEntity(type: Venue): VenueEntity =
        VenueEntity(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            distance = type.distance,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            userCurrentLatLng = type.userCurrentLatLng
        )
}

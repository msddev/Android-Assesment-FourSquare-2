package com.mkdev.cache.mapper

import com.mkdev.cache.models.VenueCacheEntity
import com.mkdev.data.models.VenueEntity
import javax.inject.Inject

class VenueCacheMapper @Inject constructor() : CacheMapper<VenueCacheEntity, VenueEntity> {
    override fun mapFromCached(type: VenueCacheEntity): VenueEntity =
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
            userCurrentLatLng = type.userLocation
        )

    override fun mapToCached(type: VenueEntity): VenueCacheEntity =
        VenueCacheEntity(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            distance = type.distance,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            userLocation = type.userCurrentLatLng
        )


}

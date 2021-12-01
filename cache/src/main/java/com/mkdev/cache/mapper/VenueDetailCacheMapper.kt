package com.mkdev.cache.mapper

import com.mkdev.cache.models.VenueCacheEntity
import com.mkdev.data.models.VenueDetailEntity
import javax.inject.Inject

class VenueDetailCacheMapper @Inject constructor() :
    CacheMapper<VenueCacheEntity, VenueDetailEntity> {
    override fun mapFromCached(type: VenueCacheEntity): VenueDetailEntity =
        VenueDetailEntity(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            likes = 0
        )

    override fun mapToCached(type: VenueDetailEntity): VenueCacheEntity =
        VenueCacheEntity(
            id = type.id,
            name = type.name,
            latitude = type.latitude,
            longitude = type.longitude,
            address = type.address,
            categoryType = type.categoryType,
            categoryIcon = type.categoryIcon,
            picture = type.picture,
            distance = 0,
            userLocation = ""
        )

}

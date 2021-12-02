package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueEntity
import com.mkdev.remote.models.Icon
import com.mkdev.remote.models.explore.Item
import javax.inject.Inject

class NearVenueEntityMapper @Inject constructor() :
    EntityMapper<Item, VenueEntity> {
    override fun mapFromResponse(data: Item): VenueEntity {
        return VenueEntity(
            id = data.venue?.id ?: "",
            name = data.venue?.name ?: "",
            latitude = data.venue?.location?.lat ?: 0.0,
            longitude = data.venue?.location?.lng ?: 0.0,
            address = data.venue?.location?.address ?: "",
            distance = data.venue?.location?.distance ?: 0,
            categoryType = data.venue?.categories?.getOrNull(0)?.name ?: "",
            categoryIcon = getCategoryIcon(data.venue?.categories?.getOrNull(0)?.icon),
            picture = "",
            userCurrentLatLng = "",
            phone = "",
            likes = 0
        )
    }

    private fun getCategoryIcon(icon: Icon?): String =
            "${icon?.prefix}64${icon?.suffix}"
}

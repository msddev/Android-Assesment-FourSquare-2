package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueEntity
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
            categoryType = data.venue?.categories?.get(0)?.name ?: "",
            categoryIcon = "${data.venue?.categories?.get(0)?.icon?.prefix}64${
                data.venue?.categories?.get(
                    0
                )?.icon?.suffix
            }",
            picture = "${data.venue?.photos?.groups?.get(0)?.items?.get(0)?.prefix}240${
                data.venue?.photos?.groups?.get(
                    0
                )?.items?.get(0)?.suffix
            }",
            userCurrentLatLng = "",
            phone = ""
        )
    }
}

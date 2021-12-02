package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueEntity
import com.mkdev.remote.models.explore.Item
import javax.inject.Inject

class NearVenueEntityMapper @Inject constructor() :
    EntityMapper<Item, VenueEntity> {
    override fun mapFromResponse(data: Item): VenueEntity {
        return VenueEntity(
            id= data.venue.id,
            name = data.venue.name,
            latitude = data.venue.location.lat,
            longitude = data.venue.location.lng,
            address = data.venue.location.address,
            distance = data.venue.location.distance,
            categoryType = if (data.venue.categories.isNotEmpty()) data.venue.categories[0].name else "",
            categoryIcon = if (data.venue.categories.isNotEmpty()) "${data.venue.categories[0].icon.prefix}64${data.venue.categories[0].icon.suffix}" else "",
            picture = if (data.venue.photos?.groups?.isNullOrEmpty() == false && !data.venue.photos.groups[0].items.isNullOrEmpty()) "${data.venue.photos.groups[0].items[0].prefix}240${data.venue.photos.groups[0].items[0].suffix}" else "",
            userCurrentLatLng = "",
        )
    }
}

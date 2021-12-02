package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueEntity
import com.mkdev.remote.models.explore.Item
import javax.inject.Inject

class NearVenueEntityMapper @Inject constructor() :
    EntityMapper<Item, VenueEntity> {
    override fun mapFromResponse(data: Item): VenueEntity {
        return VenueEntity(
            data.venue.id,
            data.venue.name,
            data.venue.location.lat,
            data.venue.location.lng,
            data.venue.location.address,
            data.venue.location.distance,
            if (data.venue.categories.isNotEmpty()) data.venue.categories[0].name else "",
            if (data.venue.categories.isNotEmpty()) "${data.venue.categories[0].icon.prefix}64${data.venue.categories[0].icon.suffix}" else "",
            if (data.venue.photos?.groups?.isNullOrEmpty() == false && !data.venue.photos.groups[0].items.isNullOrEmpty()) "${data.venue.photos.groups[0].items[0].prefix}240${data.venue.photos.groups[0].items[0].suffix}" else ""
        )
    }
}

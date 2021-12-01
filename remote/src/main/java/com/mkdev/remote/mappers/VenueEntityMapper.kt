package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.remote.models.detail.Venue
import javax.inject.Inject

class VenueEntityMapper @Inject constructor() :
    EntityMapper<Venue, VenueDetailEntity> {
    override fun mapFromResponse(data: Venue): VenueDetailEntity {
        return VenueDetailEntity(
            data.id,
            data.name,
            data.location.lat,
            data.location.lng,
            getCompleteAddress(data.location.formattedAddress),
            if (data.categories.isNotEmpty()) data.categories[0].name else "",
            if (data.categories.isNotEmpty()) "${data.categories[0].icon.prefix}${data.categories[0].icon.suffix}" else "",
            "", //server response no data for pictures
            data.likes.count
        )
    }

    private fun getCompleteAddress(address: List<String>): String? {
        var result = ""
        address.forEach {
            result += "$it "
        }
        return result
    }
}

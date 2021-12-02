package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueDetailEntity
import com.mkdev.remote.models.detail.VenueDetail
import javax.inject.Inject

class VenueDetailEntityMapper @Inject constructor() :
    EntityMapper<VenueDetail, VenueDetailEntity> {
    override fun mapFromResponse(data: VenueDetail): VenueDetailEntity {
        return VenueDetailEntity(
            id = data.id,
            name = data.name,
            latitude = data.location.lat,
            longitude = data.location.lng,
            address = getCompleteAddress(data.location.formattedAddress),
            categoryType = if (data.categories.isNotEmpty()) data.categories[0].name else "",
            categoryIcon = if (data.categories.isNotEmpty()) "${data.categories[0].icon.prefix}${data.categories[0].icon.suffix}" else "",
            picture = if (data.photo.prefix.isNotEmpty() && data.photo.suffix.isNotEmpty())
                data.photo.prefix.plus(400).plus(data.photo.suffix) else "",
            likes = data.likes.count,
            phone = if (data.photo.prefix.isNotEmpty()) data.contact.phone else ""
        )
    }

    private fun getCompleteAddress(address: List<String>): String {
        var result = ""
        address.onEach {
            result += "$it "
        }
        return result
    }
}

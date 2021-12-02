package com.mkdev.remote.mappers

import com.mkdev.data.models.VenueEntity
import com.mkdev.remote.models.detail.VenueDetail
import javax.inject.Inject

class VenueDetailEntityMapper @Inject constructor() :
    EntityMapper<VenueDetail, VenueEntity> {
    override fun mapFromResponse(data: VenueDetail): VenueEntity {
        return VenueEntity(
            id = data.id ?: "",
            name = data.name ?: "",
            latitude = data.location?.lat ?: 0.0,
            longitude = data.location?.lng ?: 0.0,
            address = getCompleteAddress(data.location?.formattedAddress),
            categoryType = data.categories?.getOrNull(0)?.name ?: "",
            categoryIcon = "${data.categories?.getOrNull(0)?.icon?.prefix}64${data.categories?.getOrNull(0)?.icon?.suffix}",
            picture = data.photo?.prefix?.plus(400)?.plus(data.photo.suffix) ?: "",
            likes = data.likes?.count ?: 0,
            phone = data.contact?.phone ?: "",
            distance = 0,
            userCurrentLatLng = ""
        )
    }

    private fun getCompleteAddress(address: List<String>?): String {
        if (address.isNullOrEmpty()) return ""
        var result = ""
        address.onEach {
            result += "$it "
        }
        return result
    }
}

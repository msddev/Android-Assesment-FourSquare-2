package com.mkdev.remote.models

import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.detail.VenueDetail

data class VenueDetailResponse(
    @SerializedName("venue")
    val venueDetail: VenueDetail
)
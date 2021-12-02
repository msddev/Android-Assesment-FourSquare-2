package com.mkdev.remote.models

import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.detail.Venue

data class VenueDetailResponse(
    @SerializedName("venue")
    val venue: Venue
)
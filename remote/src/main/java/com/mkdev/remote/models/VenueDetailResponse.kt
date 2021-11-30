package com.mkdev.remote.models


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.Meta
import com.mkdev.remote.models.detail.Response

data class VenueDetailResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("response")
    val response: Response
)
package com.mkdev.remote.models


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.explore.Response

data class ExploreVenuesResponse(
    @SerializedName("meta")
    val meta: Meta,
    @SerializedName("response")
    val response: Response
)
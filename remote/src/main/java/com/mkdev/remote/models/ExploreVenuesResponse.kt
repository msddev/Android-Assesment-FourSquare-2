package com.mkdev.remote.models


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.explore.Group

data class ExploreVenuesResponse(
    @SerializedName("totalResults")
    val totalResults: Int? = 0,
    @SerializedName("groups")
    val groups: List<Group>? = listOf()
)
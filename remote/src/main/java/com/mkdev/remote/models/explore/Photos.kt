package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName

data class Photos(
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("groups")
    val groups: List<PhotoGroup>? = listOf()
)
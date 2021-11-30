package com.mkdev.remote.models.explore


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.explore.PhotoGroup

data class Photos(
    @SerializedName("count")
    val count: Int,
    @SerializedName("groups")
    val groups: List<PhotoGroup>
)
package com.mkdev.remote.models.explore


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.explore.Group

data class Response(
    @SerializedName("totalResults")
    val totalResults: Int,
    @SerializedName("groups")
    val groups: List<Group>
)
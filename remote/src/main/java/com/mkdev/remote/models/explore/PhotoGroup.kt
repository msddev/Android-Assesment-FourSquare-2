package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName

data class PhotoGroup(
    @SerializedName("type")
    val type: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("count")
    val count: Int? = 0,
    @SerializedName("items")
    val items: List<ItemPhotoGroup>? = listOf()
)
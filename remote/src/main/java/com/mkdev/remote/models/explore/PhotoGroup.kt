package com.mkdev.remote.models.explore


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.explore.ItemPhotoGroup

data class PhotoGroup(
    @SerializedName("type")
    val type: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("count")
    val count: Int,
    @SerializedName("items")
    val items: List<ItemPhotoGroup>
)
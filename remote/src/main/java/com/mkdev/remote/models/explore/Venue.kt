package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.Category

data class Venue(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("name")
    val name: String? = "",
    @SerializedName("location")
    val location: Location? = Location(),
    @SerializedName("categories")
    val categories: List<Category>? = listOf(),
    @SerializedName("photos")
    val photos: Photos? = Photos()
)
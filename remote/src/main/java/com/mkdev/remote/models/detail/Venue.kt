package com.mkdev.remote.models.detail


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.Category

data class Venue(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: Location,
    @SerializedName("categories")
    val categories: List<Category>,
    @SerializedName("likes")
    val likes: Likes,
    @SerializedName("dislike")
    val dislike: Boolean,
    @SerializedName("hereNow")
    val hereNow: HereNow
)
package com.mkdev.remote.models.detail


import com.google.gson.annotations.SerializedName
import com.mkdev.remote.models.Category

data class Venue(
    @SerializedName("id")
    val id: String = "",
    @SerializedName("name")
    val name: String = "",
    @SerializedName("location")
    val location: Location = Location(),
    @SerializedName("categories")
    val categories: List<Category> = listOf(),
    @SerializedName("likes")
    val likes: Likes = Likes(),
    @SerializedName("dislike")
    val dislike: Boolean = false,
    @SerializedName("hereNow")
    val hereNow: HereNow = HereNow()
)
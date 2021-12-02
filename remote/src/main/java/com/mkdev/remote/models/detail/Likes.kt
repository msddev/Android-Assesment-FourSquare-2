package com.mkdev.remote.models.detail


import com.google.gson.annotations.SerializedName

data class Likes(
    @SerializedName("count")
    val count: Int? = 0
)
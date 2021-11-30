package com.mkdev.remote.models.detail


import com.google.gson.annotations.SerializedName

data class Response(
    @SerializedName("venue")
    val venue: Venue
)
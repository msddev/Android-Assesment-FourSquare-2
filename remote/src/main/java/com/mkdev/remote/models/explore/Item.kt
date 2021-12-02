package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName

data class Item(
    @SerializedName("venue")
    val venue: Venue?=Venue(),
    @SerializedName("referralId")
    val referralId: String?=""
)
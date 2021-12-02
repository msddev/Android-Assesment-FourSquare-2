package com.mkdev.remote.models.detail

import com.google.gson.annotations.SerializedName

data class Contact(
    @SerializedName("phone")
    val phone: String? = "0",
    @SerializedName("formattedPhone")
    val formattedPhone: String? = "0",
)
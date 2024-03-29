package com.mkdev.remote.models


import com.google.gson.annotations.SerializedName

data class Meta(
    @SerializedName("code")
    val code: Int? = 0,
    @SerializedName("requestId")
    val requestId: String? = ""
)
package com.mkdev.remote.models.detail

import com.google.gson.annotations.SerializedName

data class Photo(
    @SerializedName("id")
    val id: String? = "",
    @SerializedName("createdAt")
    val createdAt: Int? = 0,
    @SerializedName("prefix")
    val prefix: String? = "",
    @SerializedName("suffix")
    val suffix: String? = "",
    @SerializedName("width")
    val width: Int? = 0,
    @SerializedName("height")
    val height: Int? = 0,
    @SerializedName("visibility")
    val visibility: String? = ""

)
package com.mkdev.remote.models.explore

import com.google.gson.annotations.SerializedName

data class ItemPhotoGroup(
    @SerializedName("id")
    val id: String?="",
    @SerializedName("prefix")
    val prefix: String?="",
    @SerializedName("suffix")
    val suffix: String?=""
)
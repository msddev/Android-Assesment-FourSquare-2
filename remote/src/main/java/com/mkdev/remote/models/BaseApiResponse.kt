package com.mkdev.remote.models

import com.google.gson.annotations.SerializedName

class BaseApiResponse<T> {
    @SerializedName("meta")
    var meta: Meta? = null
    @SerializedName("response")
    var data: T? = null
}
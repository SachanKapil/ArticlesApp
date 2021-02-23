package com.articles.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

/**
 * Created by {Kapil Sachan} on 18/11/2020.
 */

data class BaseResponse<T>(

    @SerializedName("status")
    @Expose
    var status: String,
    @SerializedName("items")
    @Expose
    val items: T? = null
)
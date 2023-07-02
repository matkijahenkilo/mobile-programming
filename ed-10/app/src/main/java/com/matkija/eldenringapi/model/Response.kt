package com.matkija.eldenringapi.model

import com.google.gson.annotations.SerializedName

data class Response<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("count")   val count: Int,
    @SerializedName("total")   val total: Int,
    @SerializedName("data")    val data: ArrayList<T>
)

package com.matkija.eldenringapi.model

import com.google.gson.annotations.SerializedName

data class Attribute(
    @SerializedName("name") val name: String,
    @SerializedName("amount", alternate = ["scaling"]) val amount: String,
)

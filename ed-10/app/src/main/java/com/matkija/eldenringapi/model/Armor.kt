package com.matkija.eldenringapi.model

import com.google.gson.annotations.SerializedName

data class Armor(
    @SerializedName("id")          val id: String,
    @SerializedName("name")        val name: String,
    @SerializedName("image")       val image: String,
    @SerializedName("description") val description: String,
    @SerializedName("category")    val category: String,
    @SerializedName("weight")      val weight: String,
    @SerializedName("dmgNegation") val dmgNegation: List<Attribute>,
    @SerializedName("resistance")  val resistance: List<Attribute>
)
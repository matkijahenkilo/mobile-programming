package com.matkija.eldenringapi.model

import com.google.gson.annotations.SerializedName

data class Weapon(
    @SerializedName("id")                  val id: String,
    @SerializedName("name")                val name: String,
    @SerializedName("image")               val image: String,
    @SerializedName("description")         val description: String,
    @SerializedName("category")            val category: String,
    @SerializedName("weight")              val weight: String,
    @SerializedName("attack")              val attack: List<Attribute>,
    @SerializedName("defense")             val defense: List<Attribute>,
    @SerializedName("scalesWith")          val scalesWith: List<Attribute>,
    @SerializedName("requiredAttributes")  val requiredAttributes: List<Attribute>
)
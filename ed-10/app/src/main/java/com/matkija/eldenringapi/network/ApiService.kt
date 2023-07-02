package com.matkija.eldenringapi.network

import com.matkija.eldenringapi.model.Armor
import com.matkija.eldenringapi.model.Response
import com.matkija.eldenringapi.model.Weapon
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("armors")
    fun listArmors(@Query("limit") limit: Int): Call<Response<Armor>>

    @GET("weapons")
    fun listWeapons(@Query("limit") limit: Int): Call<Response<Weapon>>
}
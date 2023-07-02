package com.matkija.eldenringapi.listener

interface CardListener {
    fun onCardClick(item: Any)
    fun onShareClick (name: String, description: String, image: String)
}
package com.example.app.model

enum class PlaceType {
    Fun,
    Museum,
    Shopping,
    Food,
    Landmark,
    Zoo,
    Hotel
}

data class Place(
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val city: String,
    val image: String,
    val imageCredit: String,
    val types: Set<PlaceType>,
    val website: String,
    val hours: String,
    val price: String
)
package com.example.app.data

import androidx.annotation.DrawableRes

enum class PlaceType {
    Fun,
    Museum,
    Shopping,
    Food,
    Landmark,
    Zoo
}

data class Image(
    @DrawableRes
    val image: Int,
    val desc: String
)

data class Address(
    val number: Int,
    val address: String,
    val city: String
)

data class Place(
    val name: String,
    val description: String,
    @DrawableRes
    val image: Int,
    val address: Address,
    val types: Set<PlaceType>,
    val url: String = "",
    val time: String = "Open 24/7",
    val price: String = "Free"
)
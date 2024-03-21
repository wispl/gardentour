package com.example.app.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.data.model.Place
import com.example.app.data.model.PlaceType

@Entity(tableName = "places")
data class PlaceEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val description: String,
    val address: String,
    val city: String,
    val image: String,
    @ColumnInfo(name = "image_credit")
    val imageCredit: String,
    val types: String,
    val website: String,
    val hours: String,
    val price: String
)

fun PlaceEntity.toExternalModel() = Place(
    id = id,
    name = name,
    description = description,
    address = address,
    city = city,
    image = image,
    imageCredit = imageCredit,
    types = types.split(",").map { PlaceType.valueOf(it) }.toSet(),
    website = website,
    hours = hours,
    price = price
)
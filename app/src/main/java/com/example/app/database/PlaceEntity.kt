package com.example.app.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.app.data.model.Place


// TODO: Store the whole object instead of just the name
@Entity(tableName = "Places")
data class PlaceEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val name: String,
)

fun Place.toEntity(): PlaceEntity = PlaceEntity(id = id, name = name)
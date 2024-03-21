package com.example.app.database.model

import androidx.room.Entity
import androidx.room.Fts4

@Fts4
@Entity(tableName = "places_fts")
data class PlaceFtsEntity(
    val name: String,
    val address: String,
    val city: String,
)
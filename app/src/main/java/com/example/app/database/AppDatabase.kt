package com.example.app.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.app.database.dao.CityDao
import com.example.app.database.dao.PlaceDao
import com.example.app.database.dao.PlaceFtsDao
import com.example.app.database.model.CityEntity
import com.example.app.database.model.PlaceEntity
import com.example.app.database.model.PlaceFtsEntity

@Database(
    entities = [
        PlaceEntity::class,
        PlaceFtsEntity::class,
        CityEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun placeDao(): PlaceDao
    abstract fun placeFtsDao(): PlaceFtsDao
    abstract fun cityDao(): CityDao
}
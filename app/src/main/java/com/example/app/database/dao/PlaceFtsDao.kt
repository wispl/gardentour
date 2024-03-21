package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceFtsDao {
    @Query(value = "SELECT name FROM places_fts WHERE places_fts MATCH :query")
    fun searchPlaces(query: String): Flow<List<String>>
}
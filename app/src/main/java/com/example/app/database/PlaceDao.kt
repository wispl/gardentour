package com.example.app.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(place: PlaceEntity)

    @Delete
    suspend fun delete(place: PlaceEntity)

    @Query("Select * from places ORDER by name ASC")
    fun getPlaces(): Flow<List<PlaceEntity>>
}
package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.app.database.model.PlaceEntity
import com.example.app.model.PlaceType
import kotlinx.coroutines.flow.Flow

@Dao
interface PlaceDao {
    @Query(
        value = """
        SELECT * FROM places
        WHERE 
            CASE WHEN :useNamesFilter
                THEN name in (:namesFilter)
                ELSE 1
            END
        AND
            CASE WHEN :useTypesFilter
                THEN types IN (:typesFilter)
                ELSE 1
            END
        AND
            CASE WHEN :useCityFilter
                THEN city = :cityFilter
                ELSE 1
            END
    """
    )
    fun getPlaces(
        useNamesFilter: Boolean = false,
        namesFilter: Set<String> = emptySet(),
        useTypesFilter: Boolean = false,
        typesFilter: Set<PlaceType> = emptySet(),
        useCityFilter: Boolean = false,
        cityFilter: String = ""
    ): Flow<List<PlaceEntity>>

    @Query(value = "SELECT * FROM places WHERE name = :name")
    fun getPlace(name: String): Flow<PlaceEntity>

    @Query(value = "SELECT * FROM places ORDER BY RANDOM() LIMIT 1")
    fun getRandomPlace(): Flow<PlaceEntity>
}
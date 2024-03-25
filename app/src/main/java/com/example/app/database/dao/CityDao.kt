package com.example.app.database.dao

import androidx.room.Dao
import androidx.room.Query
import com.example.app.database.model.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {
    @Query(
        value = """
        SELECT * FROM cities
        WHERE 
            CASE WHEN :useNamesFilter
                THEN name in (:namesFilter)
                ELSE 1
            END
    """
    )
    fun getCities(
        useNamesFilter: Boolean = false,
        namesFilter: Set<String> = emptySet()
    ): Flow<List<CityEntity>>

    @Query(value = "SELECT * FROM cities WHERE name = :name")
    fun getCity(name: String): Flow<CityEntity>
}
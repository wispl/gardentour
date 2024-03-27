package com.example.app.data

import com.example.app.model.Place
import com.example.app.model.PlaceType
import kotlinx.coroutines.flow.Flow

data class PlaceFilterQuery(
    val city: String? = null,
    val types: Set<PlaceType>? = null
)

interface PlacesRepository {
    fun getAllPlaces(): Flow<List<Place>>
    fun getSavedPlaces(): Flow<List<Place>>
    fun getPlaces(filterQuery: PlaceFilterQuery): Flow<List<Place>>
    fun getPlace(name: String): Flow<Place>
    fun getRandomPlaces(): Flow<List<Place>>
}
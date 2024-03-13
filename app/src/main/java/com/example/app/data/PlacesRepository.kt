package com.example.app.data

import com.example.app.data.model.Place
import com.example.app.data.model.PlaceType

data class PlaceFilterQuery(
    val name: String = "",
    val city: String = "",
    val types: Set<PlaceType> = emptySet()
)

interface PlacesRepository {
    fun getRandomPlace(): Place
    fun getPlaces(): List<Place>
    fun getPlaces(filterQuery: PlaceFilterQuery): List<Place>
}
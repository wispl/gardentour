package com.example.app.data

object SearchRepository {
    // Splits place names into words and group them, forming buckets to reduce
    // searching the full repository
    private val placesBucketMap = PlaceRepository.allPlaces()
        .map { place -> place.name.split(" ").map { it.lowercase() to place } }
        .flatten()
        .groupBy({ it.first }, {it.second})

    fun search(str: String): List<Place> {
        if (str.isEmpty()) {
            return emptyList()
        }
        return placesBucketMap
            .filter { str.lowercase() in it.key }
            .values
            .flatten()
            .distinct()
    }
}

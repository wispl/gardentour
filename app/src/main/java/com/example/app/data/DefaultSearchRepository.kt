package com.example.app.data

import com.example.app.data.model.Place
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    placesRepository: PlacesRepository
) : SearchRepository {

    // Splits place names into words and group them, forming buckets to reduce
    // searching the full repository
    private val placesBucketMap = placesRepository.getPlaces()
        .map { place -> place.name.split(" ").map { it.lowercase() to place } }
        .flatten()
        .groupBy({ it.first }, {it.second})

    private val recentSearches = mutableListOf<String>()

    override fun search(str: String): List<Place> {
        if (str.isEmpty()) {
            return emptyList()
        }

        recentSearches += str
        return placesBucketMap
            .filter { str.lowercase() in it.key }
            .values
            .flatten()
            .distinct()
    }

    override fun getRecentSearches(): List<String> {
        return recentSearches
    }

    override fun clearRecentSearches() {
        recentSearches.clear()
    }
}

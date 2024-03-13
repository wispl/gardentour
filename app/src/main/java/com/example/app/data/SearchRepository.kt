package com.example.app.data

import com.example.app.data.model.Place

interface SearchRepository {
    fun search(str: String): List<Place>

    fun getRecentSearches(): List<String>

    fun clearRecentSearches()
}

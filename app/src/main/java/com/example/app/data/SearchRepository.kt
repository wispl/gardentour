package com.example.app.data

import com.example.app.model.Place
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun search(query: String): Flow<List<Place>>
}

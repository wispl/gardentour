package com.example.app.data

import com.example.app.model.City
import kotlinx.coroutines.flow.Flow

interface CitiesRepository {
    fun getCity(name: String): Flow<City>
    fun getCities(): Flow<List<City>>
    fun getSavedCities(): Flow<List<City>>
}
package com.example.app.data

import com.example.app.data.model.City

interface CitiesRepository {

    fun getCity(name: String): City

    fun allCities(): List<City>
}
package com.example.app.data

import com.example.app.data.datasource.CitiesDatasource
import com.example.app.data.model.City
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineCitiesRepository @Inject constructor(): CitiesRepository {
    // TODO: Add proper image later
    private val citieDatasource = CitiesDatasource()
    override fun getCity(name: String): City {
        return citieDatasource.cities[name]!!
    }

    override fun allCities(): List<City> {
        return citieDatasource.cities.values.toList()
    }
}

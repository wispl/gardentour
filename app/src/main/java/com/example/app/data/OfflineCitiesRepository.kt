package com.example.app.data

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

class CitiesDatasource {
    val cities = mapOf(
        "Cape May City" to City(
            name = "Cape May City",
            description = "Perhaps one of New Jersey's best seaside resort, Cape May is filled with amazing sights and unique places. While known for its beaches, Cape May offers many other interesting experiences, home to Alpacas, winderies, and fascinating landmarks",
            image = 3
        ),
    )
}
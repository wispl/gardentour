package com.example.app.data

object CityRepository {
    // TODO: Add proper image later
    private val cityMap = mapOf(
        "Cape May City" to City(
            name = "Cape May City",
            description = "Perhaps one of New Jersey's best seaside resort, Cape May is filled with amazing sights and unique places. While known for its beaches, Cape May offers many other interesting experiences, home to Alpacas, winderies, and fascinating landmarks",
            image = 3
        ),
    )

    fun getCity(name: String): City {
        return cityMap[name]!!
    }

    fun allCities(): List<City> {
        return cityMap.values.toList()
    }
}
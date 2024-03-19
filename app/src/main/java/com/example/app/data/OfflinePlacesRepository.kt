package com.example.app.data

import com.example.app.data.datasource.PlacesDatasource
import com.example.app.data.model.*
import com.example.app.database.PlaceDao
import com.example.app.database.PlaceEntity
import com.example.app.database.toEntity
import kotlinx.coroutines.flow.*
import java.util.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflinePlacesRepository @Inject constructor(
    private val placeDao: PlaceDao
) : PlacesRepository{
    private val datasource = PlacesDatasource()
    private val random = Random()

    override fun getRandomPlace(): Place {
        return datasource
            .places
            .values
            .elementAt(random.nextInt(datasource.places.size))
    }

    override fun getPlaces(): List<Place> {
        return datasource.places.values.toList()

    }

    override fun getPlaces(filterQuery: PlaceFilterQuery): List<Place> {
        return datasource.places.values.toList()
            .filter { filterQuery.city.isEmpty() || it.city == filterQuery.city }
            .filter { filterQuery.types.isEmpty() || it.types.intersect(filterQuery.types).isNotEmpty() }
            .filter { filterQuery.name.isEmpty() || it.name == filterQuery.name }
    }

    override fun getFavoritedPlaces(): Flow<List<PlaceEntity>> = placeDao.getPlaces()

    override suspend fun setFavoritePlace(place: Place) {
        placeDao.insert(place.toEntity())
    }

    override suspend fun removeFavoritePlace(place: Place) {
        placeDao.delete(place.toEntity())
    }
}
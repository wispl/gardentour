package com.example.app.data

import com.example.app.model.Place
import com.example.app.database.dao.PlaceDao
import com.example.app.database.model.PlaceEntity
import com.example.app.database.model.toExternalModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflinePlacesRepository @Inject constructor(
    private val placeDao: PlaceDao
) : PlacesRepository{
    override fun getAllPlaces(): Flow<List<Place>> {
        return placeDao.getPlaces().map { it.map(PlaceEntity::toExternalModel) }
    }

    override fun getPlaces(filterQuery: PlaceFilterQuery): Flow<List<Place>> {
        return placeDao.getPlaces(
            useTypesFilter = filterQuery.types != null,
            typesFilter = filterQuery.types ?: emptySet(),
            useCityFilter = filterQuery.city != null,
            cityFilter = filterQuery.city ?: ""
        )
        .map { it.map(PlaceEntity::toExternalModel) }
    }

    override fun getPlace(name: String): Flow<Place> {
        return placeDao.getPlace(name).map(PlaceEntity::toExternalModel)
    }

    override fun getRandomPlace(): Flow<Place> {
        return placeDao.getRandomPlace().map(PlaceEntity::toExternalModel)
    }
}
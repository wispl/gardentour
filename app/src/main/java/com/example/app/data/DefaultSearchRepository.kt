package com.example.app.data

import com.example.app.database.dao.PlaceDao
import com.example.app.database.dao.PlaceFtsDao
import com.example.app.database.model.toExternalModel
import com.example.app.model.Place
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class DefaultSearchRepository @Inject constructor(
    private val placeDao: PlaceDao,
    private val placeFtsDao: PlaceFtsDao
) : SearchRepository {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun search(query: String): Flow<List<Place>> {
        return placeFtsDao.searchPlaces("*$query*")
            .mapLatest { it.toSet() }
            .distinctUntilChanged()
            .flatMapLatest {
                placeDao.getPlaces(useNamesFilter = true, namesFilter = it)
            }
            .map { it.map { it.toExternalModel() } }
    }
}
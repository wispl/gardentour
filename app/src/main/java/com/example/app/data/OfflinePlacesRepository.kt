package com.example.app.data

import com.example.app.database.dao.PlaceDao
import com.example.app.database.model.PlaceEntity
import com.example.app.database.model.toExternalModel
import com.example.app.model.Place
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflinePlacesRepository @Inject constructor(
    private val placeDao: PlaceDao,
    private val userDataRepository: UserDataRepository
) : PlacesRepository {
    override fun getAllPlaces(): Flow<List<Place>> {
        return placeDao.getPlaces().map { it.map(PlaceEntity::toExternalModel) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSavedPlaces(): Flow<List<Place>> {
        return userDataRepository
            .userData
            .map { it.savedPlaces }
            .distinctUntilChanged()
            .flatMapLatest { followedPlaces ->
                when {
                    followedPlaces.isEmpty() -> flowOf(emptyList())
                    else -> {
                        placeDao.getPlaces(
                            useNamesFilter = true,
                            namesFilter = followedPlaces,
                        ).map { it.map { entity -> entity.toExternalModel() } }
                    }
                }
            }
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

    override fun getRandomPlaces(): Flow<List<Place>> {
        return placeDao.getRandomPlaces().map { it.map(PlaceEntity::toExternalModel) }
    }
}
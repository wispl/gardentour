package com.example.app.data

import com.example.app.database.dao.CityDao
import com.example.app.database.model.CityEntity
import com.example.app.database.model.toExternalModel
import com.example.app.model.City
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineCitiesRepository @Inject constructor(
    private val cityDao: CityDao,
    private val userDataRepository: UserDataRepository

) : CitiesRepository {
    override fun getCity(name: String): Flow<City> {
        return cityDao.getCity(name).map(CityEntity::toExternalModel)
    }

    override fun getCities(): Flow<List<City>> {
        return cityDao.getCities()
            .map { it.map(CityEntity::toExternalModel) }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun getSavedCities(): Flow<List<City>> {
        return userDataRepository
            .userData
            .map { it.savedCities }
            .distinctUntilChanged()
            .flatMapLatest { followedCities ->
                when {
                    followedCities.isEmpty() -> flowOf(emptyList())
                    else -> {
                        cityDao.getCities(
                            useNamesFilter = true,
                            namesFilter = followedCities,
                        ).map { it.map { entity -> entity.toExternalModel() } }
                    }
                }
            }
    }
}

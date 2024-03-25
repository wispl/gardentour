package com.example.app.data

import com.example.app.database.dao.CityDao
import com.example.app.database.model.CityEntity
import com.example.app.database.model.toExternalModel
import com.example.app.model.City
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OfflineCitiesRepository @Inject constructor(
    private val cityDao: CityDao
) : CitiesRepository {
    override fun getCity(name: String): Flow<City> {
        return cityDao.getCity(name).map(CityEntity::toExternalModel)
    }

    override fun getCities(): Flow<List<City>> {
        return cityDao.getCities()
            .map { it.map(CityEntity::toExternalModel) }
    }
}

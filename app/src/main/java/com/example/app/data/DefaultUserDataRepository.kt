package com.example.app.data

import com.example.app.database.model.UserData
import com.example.app.datastore.PreferencesDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class DefaultUserDataRepository @Inject constructor(
    private val preferencesDataSource: PreferencesDataSource
) : UserDataRepository {
    override val userData: Flow<UserData> = preferencesDataSource.userData

    override suspend fun setSavedCity(cityName: String, saved: Boolean) {
        preferencesDataSource.setSavedCity(cityName, saved)
    }

    override suspend fun setSavedPlace(placeName: String, saved: Boolean) {
        preferencesDataSource.setSavedPlace(placeName, saved)
    }
}
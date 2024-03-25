package com.example.app.datastore

import androidx.datastore.core.DataStore
import com.example.app.database.model.UserData
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class PreferencesDataSource @Inject constructor(
    private val userPreferences: DataStore<UserPreferences>
) {
    val userData = userPreferences.data
        .map {
            UserData(
                savedCities = it.savedCitiesMap.keys,
                savedPlaces = it.savedPlacesMap.keys
            )
        }

    suspend fun setSavedCity(cityName: String, saved: Boolean) {
        userPreferences.updateData {
            it.copy {
                if (saved) {
                    savedCities.put(cityName, true)
                } else {
                    savedCities.remove(cityName)
                }
            }
        }
    }

    suspend fun setSavedPlace(placeName: String, saved: Boolean) {
        userPreferences.updateData {
            it.copy {
                if (saved) {
                    savedPlaces.put(placeName, true)
                } else {
                    savedPlaces.remove(placeName)
                }
            }
        }
    }
}
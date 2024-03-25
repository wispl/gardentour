package com.example.app.data

import com.example.app.database.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserDataRepository {
    val userData: Flow<UserData>

    suspend fun setSavedCity(cityName: String, saved: Boolean)
    suspend fun setSavedPlace(placeName: String, saved: Boolean)
}
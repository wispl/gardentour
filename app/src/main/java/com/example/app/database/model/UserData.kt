package com.example.app.database.model

data class UserData(
    val savedCities: Set<String>,
    val savedPlaces: Set<String>
)
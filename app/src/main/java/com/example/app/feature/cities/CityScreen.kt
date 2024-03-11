package com.example.app.feature.cities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.data.CityRepository

const val CITIES_ROUTE = "city"

@Composable
private fun CitiesScreen(onClick: (String) -> Unit) {
    Column {
        LazyColumn {
            items(CityRepository.allCities()) {
                it -> Text(it.name)
            }
        }
    }
}

fun NavGraphBuilder.citiesScreen(onPlaceClick: (String) -> Unit) {
    composable(route = CITIES_ROUTE) {
        CitiesScreen(onPlaceClick)
    }
}

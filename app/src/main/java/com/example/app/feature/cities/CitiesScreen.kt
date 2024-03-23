package com.example.app.feature.cities

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.components.CityCard

const val CITIES_ROUTE = "city"

fun NavGraphBuilder.citiesScreen(onPlaceClick: (String) -> Unit) {
    composable(route = CITIES_ROUTE) {
        CitiesScreen(onPlaceClick)
    }
}

@Composable
private fun CitiesScreen(
    onClick: (String) -> Unit,
    viewModel: CitiesViewModel = hiltViewModel()
) {
    val cities by viewModel.cities.collectAsStateWithLifecycle()
    Column {
        LazyColumn {
            items(cities) {
                CityCard(it, {})
            }
        }
    }
}
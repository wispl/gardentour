package com.example.app.feature.cities

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.components.CityCard

const val CITIES_LIST_ROUTE = "citiesList"

fun NavGraphBuilder.citiesListScreen(onCityClick: (String) -> Unit) {
    composable(route = CITIES_LIST_ROUTE) {
        CitiesListScreen( { onCityClick(it) })
    }
}
@Composable
private fun CitiesListScreen(
    onCityClick: (String) -> Unit,
    citiesViewModel: CitiesListViewModel = hiltViewModel()
) {
    val cities by citiesViewModel.cities.collectAsStateWithLifecycle()
    LazyColumn {
        items(cities) {
            CityCard(it, { onCityClick(it.name) })
        }
    }
}
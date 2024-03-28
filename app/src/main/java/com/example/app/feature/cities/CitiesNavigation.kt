package com.example.app.feature.cities

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.app.feature.cities.city.cityScreen

const val CITIES_ROUTE = "cities"

fun NavGraphBuilder.citiesScreen(
    onCityClick: (String) -> Unit,
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit
) {
    navigation(
        startDestination = CITIES_LIST_ROUTE,
        route = CITIES_ROUTE
    ) {
        citiesListScreen(onCityClick)
        cityScreen(
            onBackClick = onBackClick,
            onPlaceClick = onPlaceClick
        )
    }
}

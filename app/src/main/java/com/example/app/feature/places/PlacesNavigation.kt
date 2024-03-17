package com.example.app.feature.places

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.navigation
import com.example.app.feature.places.place.placeScreen

const val PLACES_ROUTE = "PLACES"
fun NavGraphBuilder.placesScreen(
    onPlaceClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    navigation(startDestination = PLACES_LIST_ROUTE, route = PLACES_ROUTE) {
        placesListScreen(onPlaceClick)
        placeScreen(onBackClick)
    }
}

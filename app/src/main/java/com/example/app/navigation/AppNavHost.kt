package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.app.feature.cities.citiesScreen
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.home.homeScreen
import com.example.app.feature.places.place.navigateToPlace
import com.example.app.feature.places.placesScreen
import com.example.app.feature.search.searchScreen

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
    startDestination: String = HOME_ROUTE
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        homeScreen(navController::navigateToPlace)
        placesScreen(
            onPlaceClick = navController::navigateToPlace,
            onBackClick = navController::navigateUp
        )
        searchScreen(
            onPlaceClick = navController::navigateToPlace,
            onBackClick = navController::navigateUp
        )
        citiesScreen(navController::navigateToPlace)
    }
}
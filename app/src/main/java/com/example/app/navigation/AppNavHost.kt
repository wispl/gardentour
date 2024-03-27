package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.app.feature.cities.citiesScreen
import com.example.app.feature.cities.city.navigateToCity
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.home.homeScreen
import com.example.app.feature.places.place.navigateToPlace
import com.example.app.feature.places.placesScreen
import com.example.app.feature.saved.savedScreen
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
        homeScreen(
            onPlaceClick = navController::navigateToPlace,
            onCityClick = navController::navigateToCity,
        )
        placesScreen(
            onPlaceClick = navController::navigateToPlace,
            onBackClick = navController::navigateUp
        )
        searchScreen(
            onPlaceClick = navController::navigateToPlace,
            onBackClick = navController::navigateUp
        )
        citiesScreen(
            onCityClick = navController::navigateToCity,
            onBackClick = navController::navigateUp
        )
        savedScreen(
            onPlaceClick = navController::navigateToPlace,
            onCityClick = navController::navigateToCity,
        )
    }
}
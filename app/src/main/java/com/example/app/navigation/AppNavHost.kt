package com.example.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.app.feature.cities.citiesScreen
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.home.homeScreen
import com.example.app.feature.placedetail.navigateToPlaceDetail
import com.example.app.feature.placedetail.placeDetailScreen
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
        homeScreen(navController::navigateToPlaceDetail)
        placesScreen(navController::navigateToPlaceDetail)
        searchScreen(navController::navigateToPlaceDetail)
        citiesScreen(navController::navigateToPlaceDetail)

        placeDetailScreen(navController::navigateUp)
    }
}
package com.example.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.feature.cities.CITIES_ROUTE
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.places.PLACES_ROUTE

enum class AppDestination(
    val text: Int,
    val icon: ImageVector,
    val title: Int
) {
    Home(
        text = R.string.home,
        icon = Icons.Rounded.Home,
        title = R.string.garden_tour
    ),
    Places(
        text = R.string.places,
        icon = Icons.Rounded.Place,
        title = R.string.places
    ),
    Cities(
        text = R.string.cities,
        icon = Icons.Rounded.Star,
        title = R.string.cities
    ),
    Saved(
        text = R.string.saved,
        icon = Icons.Rounded.Favorite,
        title = R.string.saved
    )
}

fun NavController.navigateToDestination(destination: AppDestination) {
    when (destination) {
        AppDestination.Home -> navigate(HOME_ROUTE)
        AppDestination.Places -> navigate(PLACES_ROUTE)
        AppDestination.Cities -> navigate(CITIES_ROUTE)
        AppDestination.Saved -> TODO()
    }
}
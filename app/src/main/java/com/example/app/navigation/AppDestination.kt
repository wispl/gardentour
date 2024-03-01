package com.example.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.example.app.R
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.places.PLACES_ROUTE

enum class AppDestination(val text: Int, val icon: ImageVector) {
    Home(
        text = R.string.home,
        icon = Icons.Default.Home
    ),
    Places(
        text = R.string.places,
        icon = Icons.Default.Place
    ),
    Search(
        text = R.string.search,
        icon = Icons.Default.Search
    ),
    Saved(
        text = R.string.saved,
        icon = Icons.Default.FavoriteBorder
    )
}

fun NavController.navigateToDestination(destination: AppDestination) {
    when (destination) {
        AppDestination.Home -> navigate(HOME_ROUTE)
        AppDestination.Places -> navigate(PLACES_ROUTE)
        AppDestination.Search -> TODO()
        AppDestination.Saved -> TODO()
    }
}
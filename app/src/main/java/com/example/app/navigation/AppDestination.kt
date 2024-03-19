package com.example.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material.icons.rounded.Star
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.navOptions
import com.example.app.R
import com.example.app.feature.cities.CITIES_ROUTE
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.places.PLACES_ROUTE
import com.example.app.feature.saved.SAVED_ROUTE
import com.example.app.feature.search.navigateToSearch

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
    val topLevelNavOptions = navOptions {
        // Pop up to the start destination of the graph to
        // avoid building up a large stack of destinations
        // on the back stack as users select items
        popUpTo(graph.findStartDestination().id) {
            saveState = true
        }
        // Avoid multiple copies of the same destination when
        // reselecting the same item
        launchSingleTop = true
        // Restore state when reselecting a previously selected item
        restoreState = true
    }
    when (destination) {
        AppDestination.Home -> navigate(HOME_ROUTE, topLevelNavOptions)
        AppDestination.Places -> navigate(PLACES_ROUTE, topLevelNavOptions)
        AppDestination.Cities -> navigate(CITIES_ROUTE, topLevelNavOptions)
        AppDestination.Saved -> navigate(SAVED_ROUTE, topLevelNavOptions)
    }
}
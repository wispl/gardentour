package com.example.app.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.data.PlaceRepository
import com.example.app.ui.ImageCard

const val HOME_ROUTE = "home"
@Composable
private fun HomeScreen(onClick: (String) -> Unit) {
    val place = PlaceRepository.getRandom()
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ImageCard(
                place.name,
                painterResource(place.image),
                place.description,
                { onClick(place.name) }
            )
        }
    }
}

fun NavGraphBuilder.homeScreen(onPlaceClick: (String) -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeScreen(onPlaceClick)
    }
}

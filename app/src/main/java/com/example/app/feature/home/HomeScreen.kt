package com.example.app.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.data.PlaceRepository
import com.example.app.ui.PlaceCard
import com.example.app.ui.theme.Typography

const val HOME_ROUTE = "home"

@Composable
private fun HomeScreen(onClick: (String) -> Unit) {
    val place = PlaceRepository.getRandom()
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Check out these places",
                style = Typography.titleMedium ,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(top = 24.dp)
            )
            Text(
                "Explore and save fascinating and amazing cities and places in New Jersey, right from your phone.",
                style = Typography.bodyMedium,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(12.dp, 8.dp)
            )

            PlaceCard(place, { onClick(place.name) })
        }
    }
}

fun NavGraphBuilder.homeScreen(onPlaceClick: (String) -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeScreen(onPlaceClick)
    }
}

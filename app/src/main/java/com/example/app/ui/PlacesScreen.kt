package com.example.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.app.data.PlaceRepository

@Composable
fun PlacesScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        LazyColumn {
            items(PlaceRepository.places().toList()) {
                place -> ImageCard(place.name, painterResource(place.image), place.description)
            }
        }
    }
}

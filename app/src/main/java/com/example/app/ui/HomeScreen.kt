package com.example.app.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import com.example.app.data.PlaceRepository

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val place = PlaceRepository.get_random();
    Column(
        modifier = modifier
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ImageCard(place.name, painterResource(place.image), place.description)
        }
    }
}
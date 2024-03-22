package com.example.app.components

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.app.model.Place

@Composable
fun PlaceCardsList(
    places: List<Place>,
    onPlaceClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier) {
        items(places) {
            PlaceCard(it, { onPlaceClick(it.name) })
        }
    }
}
package com.example.app.feature.places

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.ui.PlaceCard

const val PLACES_ROUTE = "places"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlacesScreen(onClick: (String) -> Unit, viewModel: PlacesViewModel = viewModel()) {
    val places by viewModel.places.collectAsStateWithLifecycle()
    val activeFilters by viewModel.activeFilters.collectAsStateWithLifecycle()
    val scrollState = rememberScrollState()

    Column {
        Row(
            modifier = Modifier.padding(8.dp).horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp))
        {
            viewModel.allFilters().forEach {
                FilterChip(
                    label = { Text(it.name) },
                    onClick = { viewModel.toggleFilter(it) },
                    selected = (it in activeFilters),
                    leadingIcon = if (it in activeFilters) {
                        {
                            Icon(imageVector = Icons.Filled.Done, contentDescription = "Active Filter")
                        }
                    } else {
                        null
                    }
                )
            }
        }

        LazyColumn {
            items(places) {
                place -> PlaceCard(place = place, onClick = { onClick(place.name) })
            }
        }
    }
}

fun NavGraphBuilder.placesScreen(onPlaceClick: (String) -> Unit) {
    composable(route = PLACES_ROUTE) {
        PlacesScreen(onPlaceClick)
    }
}
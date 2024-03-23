package com.example.app.feature.places

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.components.PlaceCardsList

const val PLACES_LIST_ROUTE = "placesList"

fun NavGraphBuilder.placesListScreen(onPlaceClick: (String) -> Unit) {
    composable(route = PLACES_LIST_ROUTE) {
        PlacesListScreen(onPlaceClick)
    }
}

@Composable
private fun PlacesListScreen(
    onClick: (String) -> Unit,
    viewModel: PlacesListViewModel = hiltViewModel()
) {
    val places by viewModel.places.collectAsStateWithLifecycle()
    val activeFilters by viewModel.activeFilters.collectAsStateWithLifecycle()

    val scrollState = rememberScrollState()

    Column {
        Row(
            modifier = Modifier.padding(8.dp).horizontalScroll(scrollState),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            viewModel.allFilters().forEach {
                PlaceFilterChip(it.name, it in activeFilters) {
                    viewModel.toggleFilter(it)
                }
            }
        }

        PlaceCardsList(places, onClick)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceFilterChip(
    name: String,
    selected: Boolean,
    onClick: () -> Unit,
) {
    FilterChip(
        label = { Text(name) },
        onClick = onClick,
        selected = selected,
        leadingIcon = if (selected) {
            {
                Icon(imageVector = Icons.Filled.Done, contentDescription = "Active Filter")
            }
        } else {
            null
        }
    )
}

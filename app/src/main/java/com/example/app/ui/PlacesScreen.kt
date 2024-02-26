package com.example.app.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun PlacesScreen(viewModel: PlacesViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    Column {
        TopAppBar( title = { Text("Places") } )
        FlowRow(
            modifier = Modifier.padding(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp))
        {
            viewModel.allFilters().forEach {
                FilterChip(
                    label = { Text(it.name) },
                    onClick = { viewModel.toggleFilter(it) },
                    selected = state.activeFilters.contains(it),
                    leadingIcon = if (state.activeFilters.contains(it)) {
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
            items(state.places) {
                place -> ImageCard(place.name, painterResource(place.image), place.description)
            }
        }
    }
}

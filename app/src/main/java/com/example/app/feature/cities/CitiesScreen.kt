package com.example.app.feature.cities

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val CITIES_ROUTE = "city"

@Composable
private fun CitiesScreen(
    onClick: (String) -> Unit,
    viewModel: CitiesViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Column {
        LazyColumn {
            items(uiState.cities) {
                Text(it.name)
            }
        }
    }
}

fun NavGraphBuilder.citiesScreen(onPlaceClick: (String) -> Unit) {
    composable(route = CITIES_ROUTE) {
        CitiesScreen(onPlaceClick)
    }
}

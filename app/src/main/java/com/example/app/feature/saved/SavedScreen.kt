package com.example.app.feature.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.components.CityCardsList
import com.example.app.components.PlaceCardsList

const val SAVED_ROUTE = "saved"

fun NavGraphBuilder.savedScreen(
    onPlaceClick: (String) -> Unit,
    onCityClick: (String) -> Unit
) {
    composable(route = SAVED_ROUTE) {
        SavedScreen(
            onPlaceClick = onPlaceClick,
            onCityClick = onCityClick
        )
    }
}

@Composable
fun SavedScreen(
    onPlaceClick: (String) -> Unit,
    onCityClick: (String) -> Unit,
    savedViewModel: SavedViewModel = hiltViewModel()
) {
    val savedPlaces by savedViewModel.savedPlaces.collectAsStateWithLifecycle()
    val savedCities by savedViewModel.savedCities.collectAsStateWithLifecycle()
    var tab by remember { mutableIntStateOf(0) }

    val tabs = listOf("Places", "Cities")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tab) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    content = {
                        Text(title, modifier = Modifier.padding(8.dp))
                    },
                    selected = (tab == index),
                    onClick = { tab = index },
                )
            }
        }

        when (tabs[tab]) {
            "Places" -> {
                PlaceCardsList(
                    places = savedPlaces,
                    onPlaceClick = onPlaceClick
                )
            }

            "Cities" -> {
                CityCardsList(
                    cities = savedCities,
                    onCityClick = onCityClick
                )
            }
        }
    }
}

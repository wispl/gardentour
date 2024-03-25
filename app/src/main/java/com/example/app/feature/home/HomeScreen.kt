package com.example.app.feature.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.components.PlaceCard
import com.example.app.ui.theme.Typography

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(onPlaceClick: (String) -> Unit) {
    composable(route = HOME_ROUTE) {
        HomeRoute(onPlaceClick)
    }
}

@Composable
private fun HomeRoute(
    onClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreen(uiState, onClick)
}

@Composable
private fun HomeScreen(
    homeUiState: HomeUIState,
    onClick: (String) -> Unit,
) {
    Column {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                "Check out these places",
                style = Typography.titleMedium,
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

            when (homeUiState) {
                HomeUIState.Loading -> {
                    CircularProgressIndicator()
                }

                is HomeUIState.Success -> {
                    PlaceCard(homeUiState.place, { onClick(homeUiState.place.name) })
                }

                HomeUIState.Error -> TODO()
            }
        }
    }
}
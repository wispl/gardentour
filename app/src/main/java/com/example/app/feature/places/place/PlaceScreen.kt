package com.example.app.feature.places.place

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import com.example.app.components.BackNavTopBar
import com.example.app.components.PlaceTypes
import com.example.app.components.VisitButton
import com.example.app.model.Place

const val PLACE_ID = "placeId"
const val PLACE_ROUTE = "place/{$PLACE_ID}"

fun NavGraphBuilder.placeScreen(onNavigationClick: () -> Unit) {
    composable(
        route = PLACE_ROUTE,
        arguments = listOf(
            navArgument(PLACE_ID) {
                type = NavType.StringType
            }
        )
    ) {
        PlaceRoute(onNavigationClick)
    }
}

fun NavController.navigateToPlace(placeId: String) {
    navigate("place/$placeId")
}

class PlaceArgs(val placeId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[PLACE_ID]) as String)
}

@Composable
private fun PlaceRoute(
    onNavigationClick: () -> Unit,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    PlaceScreen(
        placeUIState = uiState,
        onBackClick = onNavigationClick,
        onSavedClick = { viewModel.setSavedPlace(it) }
    )
}

@Composable
private fun PlaceScreen(
    placeUIState: PlaceUIState,
    onBackClick: () -> Unit,
    onSavedClick: (Boolean) -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (placeUIState) {
            PlaceUIState.Error -> TODO()
            PlaceUIState.Loading -> {
                CircularProgressIndicator()
            }

            is PlaceUIState.Success -> {
                val icon = if (placeUIState.isSaved) {
                    Icons.Rounded.Favorite
                } else {
                    Icons.Rounded.FavoriteBorder
                }
                BackNavTopBar(
                    text = "Place Detail",
                    imageVector = icon,
                    onBackClick = onBackClick,
                    onActionClick = { onSavedClick(!placeUIState.isSaved) }
                )
                PlaceInformation(placeUIState.place)
            }
        }
    }
}

@Composable
private fun PlaceInformation(place: Place) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        PlaceImageHeader(place.image, place.imageCredit)
        PlaceAddress(place.address, place.city)

        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Use weight to prioritize size calculation of Button first
                Text(
                    text = place.name,
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.weight(1f)
                )
                VisitButton(url = place.website) { Text("Visit") }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            PlaceTypes(place.types)

            Spacer(modifier = Modifier.padding(8.dp))
            Text(
                text = place.description,
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(8.dp))
            PlaceDetails(place.hours, place.price)
        }
    }
}

@Composable
private fun PlaceImageHeader(image: String, imageCredit: String) {
    Box(
        modifier = Modifier.height(240.dp).fillMaxHeight()
    ) {
        AsyncImage(
            model = image,
            contentDescription = imageCredit,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun PlaceAddress(address: String, city: String) {
    Surface(color = MaterialTheme.colorScheme.surfaceVariant) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = {},
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer
                )
            ) {
                Icon(
                    imageVector = Icons.Default.Place,
                    contentDescription = ""
                )
            }

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                text = "$address, $city",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun PlaceDetails(hours: String, price: String) {
    Text(
        text = "Details",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(4.dp)
    )
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Column(verticalArrangement = Arrangement.Center) {
            Text("Price", color = Color.Gray)
            Text("Hours", color = Color.Gray)
        }

        Column(verticalArrangement = Arrangement.Center) {
            Text(price)
            Text(hours)
        }
    }
}
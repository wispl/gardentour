package com.example.app.feature.cities.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
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
import com.example.app.R
import com.example.app.components.*
import com.example.app.model.City
import com.example.app.model.Place

const val CITY_ID = "cityId"
const val CITY_ROUTE_BASE = "city"
const val CITY_ROUTE = "${CITY_ROUTE_BASE}/{$CITY_ID}"

fun NavGraphBuilder.cityScreen(
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit
) {
    composable(
        route = CITY_ROUTE,
        arguments = listOf(
            navArgument(CITY_ID) {
                type = NavType.StringType
            }
        )
    ) {
        CityRoute(
            onBackClick,
            onPlaceClick
        )
    }
}

fun NavController.navigateToCity(cityId: String) {
    navigate("city/$cityId")
}

class CityArgs(val cityId: String) {
    constructor(savedStateHandle: SavedStateHandle) : this(checkNotNull(savedStateHandle[CITY_ID]) as String)
}

@Composable
private fun CityRoute(
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit,
    viewModel: CityViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CityScreen(
        cityUIState = uiState,
        onBackClick = onBackClick,
        onPlaceClick = onPlaceClick,
        onSavedClick = { viewModel.setSavedCity(it) }
    )
}

@Composable
private fun CityScreen(
    cityUIState: CityUIState,
    onBackClick: () -> Unit,
    onPlaceClick: (String) -> Unit,
    onSavedClick: (Boolean) -> Unit
) {
    Column(modifier = Modifier.fillMaxSize()) {
        when (cityUIState) {
            CityUIState.Error -> TODO()
            CityUIState.Loading -> { CircularProgressIndicator() }
            is CityUIState.Success -> {
                val icon = if (cityUIState.isSaved) {
                    Icons.Rounded.Favorite
                } else {
                    Icons.Rounded.FavoriteBorder
                }
                BackNavTopBar(
                    text = "City Details",
                    imageVector = icon,
                    onBackClick = onBackClick,
                    onActionClick = { onSavedClick(!cityUIState.isSaved) }
                )
                CityContent(
                    cityUIState.city,
                    cityUIState.places,
                    onPlaceClick = onPlaceClick
                )
            }
        }
    }
}

@Composable
private fun CityContent(
    city: City,
    places: List<Place>,
    onPlaceClick: (String) -> Unit
) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
    {
        Box(contentAlignment = Alignment.BottomEnd) {
            CityImageHeader(city.image, city.imageCredit)
            // To get the curved borders
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp),
                modifier = Modifier.height(32.dp).fillMaxWidth()
            ) {}
        }

        Column(modifier = Modifier.padding(24.dp)) {
            CityHeader(city.name, city.location, city.website)
            Spacer(modifier = Modifier.padding(4.dp))

            Text(
                text = city.description,
                style = MaterialTheme.typography.bodyLarge,
            )
            Spacer(modifier = Modifier.padding(8.dp))

            VisitButton(url = city.events, modifier = Modifier.fillMaxWidth()) {
                Text("Events", style = MaterialTheme.typography.headlineMedium)
            }
            Spacer(modifier = Modifier.height(16.dp))

            CityBestTimeImage(city.bestTime)
            Spacer(modifier = Modifier.height(16.dp))

            Text(city.bestTimeReason)
            Spacer(modifier = Modifier.height(12.dp))
            PlacesList(places, onPlaceClick = { onPlaceClick(it) })
        }
    }
}

@Composable
private fun CityImageHeader(image: String, imageCredit: String) {
    Box {
        AsyncImage(
            model = image,
            contentDescription = imageCredit,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.height(280.dp).fillMaxHeight()
        )
    }
}

@Composable
private fun CityHeader(name: String, location: String, website: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineLarge,
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Tag(enabled = true, modifier = Modifier.weight(1f)) {
            Icon(imageVector = Icons.Rounded.Place, contentDescription = "")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = location)
        }
        VisitButton(url = website) {
            Text("Visit")
        }
    }
}

@Composable
private fun CityBestTimeImage(time: String) {
    Text(
        text = "When to visit?",
        style = MaterialTheme.typography.headlineMedium
    )
    Spacer(modifier = Modifier.height(12.dp))
    Box(contentAlignment = Alignment.CenterStart) {
        Card(shape = RoundedCornerShape(16.dp)) {
            // TODO: implement images
            Image(
                painter = painterResource(R.drawable.summer),
                contentDescription = time,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(120.dp)
            )
        }

//        image sources
//        https://wallhaven.cc/w/yj32mx
//        https://wallhaven.cc/w/4lr66r
        Text(
            "   $time",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}

@Composable
private fun PlacesList(places: List<Place>, onPlaceClick: (String) -> Unit) {
    Text(
        text = "Places",
        style = MaterialTheme.typography.headlineMedium
    )
    Spacer(modifier = Modifier.height(12.dp))
    Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
        places.forEach {
            ImageCard(it.image, onClick = { onPlaceClick(it.name) })
        }
    }
}
package com.example.app.feature.cities.city

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Place
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.*
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.app.R
import com.example.app.components.Tag
import com.example.app.model.City

const val CITY_ID = "cityId"
const val CITY_ROUTE_BASE = "city"
const val CITY_ROUTE = "${CITY_ROUTE_BASE}/{$CITY_ID}"

fun NavGraphBuilder.cityScreen(onBackClick: () -> Unit) {
    composable(
        route = CITY_ROUTE,
        arguments = listOf(
            navArgument(CITY_ID) {
                type = NavType.StringType
            }
        )
    ) {
        CityRoute(onBackClick)
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
    onNavigationClick: () -> Unit,
    viewModel: CityViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CityScreen(uiState, onNavigationClick)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun CityScreen(
    cityUIState: CityUIState,
    onNavigationClick: () -> Unit,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("City Details") },
            navigationIcon = {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = ""
                    )
                }
            },
        )

        when (cityUIState) {
            CityUIState.Error -> TODO()
            CityUIState.Loading -> {
                CircularProgressIndicator()
            }
            is CityUIState.Success -> {
                CityContent(cityUIState.city)
            }
        }
    }
}

@Composable
private fun CityContent(city: City) {
    Column(modifier = Modifier.verticalScroll(rememberScrollState()))
    {
        Box(contentAlignment = Alignment.BottomEnd) {
            CityImageHeader(city.image, city.imageCredit)
            Card(
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                shape = RoundedCornerShape(48.dp, 48.dp, 0.dp, 0.dp),
                modifier = Modifier.height(32.dp).fillMaxWidth()
            ) {
            }
        }

        Column(modifier = Modifier.padding(24.dp)) {
            CityHeader(city.name, city.location, city.website)
            Spacer(modifier = Modifier.padding(4.dp))

            Spacer(modifier = Modifier.padding(8.dp))
            CityDescription(city.description)

            Spacer(modifier = Modifier.height(16.dp))
            CityBestTimeImage(city.bestTime)
            Spacer(modifier = Modifier.height(16.dp))
            Text(city.bestTimeReason)
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
        Tag(enabled = true) {
            Icon(imageVector = Icons.Rounded.Place, contentDescription = "")
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = location)
        }
        CityWebsiteButton(website)
    }
}

@Composable
private fun CityDescription(description: String) {
    Text(
        text = "Description",
        style = MaterialTheme.typography.headlineMedium
    )
    Spacer(modifier = Modifier.height(12.dp))
    Text(
        text = description,
        style = MaterialTheme.typography.bodyLarge,
    )
}

@Composable
private fun CityWebsiteButton(link: String) {
    val uriHandler = LocalUriHandler.current
    Button(
        content = { Text("Visit") },
        onClick = { uriHandler.openUri(link) },
        modifier = Modifier.width(80.dp)
    )
}

// TODO: Extract seasons out to enum
@Composable
private fun CityBestTimeImage(time: String) {
    Box(contentAlignment = Alignment.CenterStart) {
        Card(shape = RoundedCornerShape(16.dp)) {
            Image(
                painter = painterResource(R.drawable.summer),
                contentDescription = time,
                contentScale = ContentScale.Crop,
                modifier = Modifier.height(120.dp)
            )
        }
//        image source
//        https://wallhaven.cc/w/yj32mx
//        https://wallhaven.cc/w/4lr66r
        Text(
            "   Summer",
            color = Color.White,
            style = MaterialTheme.typography.headlineMedium,
        )
    }
}
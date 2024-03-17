package com.example.app.feature.placedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app.data.model.Address
import com.example.app.data.model.Hours
import com.example.app.ui.PlaceTypes

const val PLACE_ID = "placeId"
const val PLACE_ROUTE = "place/{$PLACE_ID}"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceDetailScreen(
    onNavigationClick: () -> Unit,
    viewModel: PlaceDetailViewModel = hiltViewModel()
) {
    val state by viewModel.uiState.collectAsState()
    val place = state.place.firstOrNull()

    Column(modifier = Modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text("Detail") },
            navigationIcon = {
                IconButton(onClick = onNavigationClick) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = ""
                    )
                }
            },
        )

        if (place != null) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                PlaceImageHeader(place.image)
                PlaceAddress(place.address)

                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // Use weight to prioritize size calculation of Button first
                        PlaceName(place.name, modifier = Modifier.weight(1f))
                        PlaceWebsiteButton(place.url)
                    }
                    Spacer(modifier = Modifier.padding(8.dp))
                    PlaceTypes(place.types)

                    Spacer(modifier = Modifier.padding(8.dp))
                    PlaceDescription(place.description)
                }
                Spacer(modifier = Modifier.height(4.dp))
                PlaceExtraDetails(place.time, place.price)
            }
        }
    }
}

@Composable
private fun PlaceImageHeader(image: Int) {
    Box(
        modifier = Modifier.height(240.dp).fillMaxHeight()
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun PlaceAddress(address: Address) {
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
                text = address.toString(),
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Composable
private fun PlaceName(name: String, modifier: Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        modifier = modifier
    )
}

@Composable
private fun PlaceWebsiteButton(link: String) {
    val uriHandler = LocalUriHandler.current
    Button(
        content = { Text("Visit") },
        onClick = { uriHandler.openUri(link) }
    )
}

@Composable
private fun PlaceDescription(description: String) {
    Text(text = description, style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun PlaceExtraDetails(hours: Hours, price: String) {
    Text(
        text = "Details",
        style = MaterialTheme.typography.titleLarge,
        modifier = Modifier.padding(4.dp)
    )
    Row(
        modifier = Modifier.padding(8.dp),
        horizontalArrangement = Arrangement.spacedBy(32.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
        ) {
            Text("Hours")
            Text("Price")
        }
        Column(
            verticalArrangement = Arrangement.Center
        ) {
            Text(hours.toString(), fontWeight = FontWeight.Bold)
            Text(price, fontWeight = FontWeight.Bold)
        }
    }
}

fun NavGraphBuilder.placeDetailScreen(onNavigationClick: () -> Unit) {
    composable(
        route = PLACE_ROUTE,
        arguments = listOf(navArgument(PLACE_ID) { type = NavType.StringType })
    ) {
        PlaceDetailScreen(onNavigationClick)
    }
}

fun NavController.navigateToPlaceDetail(placeId: String) {
    navigate("place/$placeId")
}

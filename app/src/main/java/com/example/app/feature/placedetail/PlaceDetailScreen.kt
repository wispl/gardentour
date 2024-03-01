package com.example.app.feature.placedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

const val PLACE_ID = "placeId"
const val PLACE_ROUTE = "place/{$PLACE_ID}"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun PlaceDetailScreen(viewModel: PlaceDetailViewModel = viewModel()) {
    val state by viewModel.uiState.collectAsState()
    Column {
        TopAppBar( title = { Text("Place Detail View") } )
        Card(modifier = Modifier.height(150.dp).fillMaxWidth()) {
            Image(
                painter = painterResource(state.place.image),
                contentDescription = null,
                contentScale = ContentScale.FillWidth,
                modifier = Modifier.fillMaxWidth()
            )
        }
        Text(text = state.place.name, style = MaterialTheme.typography.titleMedium, modifier = Modifier.padding(4.dp))
        Text(text = state.place.description, style = MaterialTheme.typography.bodyMedium)
    }
}

fun NavGraphBuilder.placeDetailScreen() {
    composable(
        route = PLACE_ROUTE,
        arguments = listOf(navArgument(PLACE_ID) { type = NavType.StringType })
    ) {
        PlaceDetailScreen()
    }
}

fun NavController.navigateToPlaceDetail(placeId: String) {
    navigate("place/$placeId")
}

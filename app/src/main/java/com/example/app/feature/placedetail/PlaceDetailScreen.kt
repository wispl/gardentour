package com.example.app.feature.placedetail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
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
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.app.data.model.Address
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
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CenterAlignedTopAppBar(
            title = { },
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
            Card(shape = RoundedCornerShape(32.dp, 32.dp, 0.dp, 0.dp)) {
                Column(
                    modifier = Modifier.fillMaxHeight().padding(16.dp).verticalScroll(rememberScrollState())
                ) {
                    PlaceImageCard(place.image)
                    Spacer(modifier = Modifier.height(4.dp))
                    PlaceName(place.name)
                    PlaceAddress(place.address)
                    PlaceWebsite(place.url)
                    Spacer(modifier = Modifier.padding(12.dp))
                    PlaceDescription(place.description)
                    Spacer(modifier = Modifier.height(16.dp))
                    PlaceExtraDetails(place.time, place.price)
                    PlaceTypes(place.types)
                }
            }
        }
    }
}

@Composable
private fun PlaceImageCard(image: Int) {
    Card(
        modifier = Modifier
            .height(240.dp)
            .fillMaxHeight(),
        shape = RoundedCornerShape(32.dp)
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
private fun PlaceName(name: String) {
    Text(
        text = name,
        style = MaterialTheme.typography.headlineMedium,
        textAlign = TextAlign.Center,
    )
}


@Composable
private fun PlaceDescription(description: String) {
    Text(text = description, style = MaterialTheme.typography.bodyLarge)
}

@Composable
private fun PlaceAddress(address: Address) {
    val str = "${address.number} ${address.address}, ${address.city}"
    Text(
        text = str,
        style = MaterialTheme.typography.titleMedium,
        textAlign = TextAlign.Center,
    )
}

@Composable
private fun PlaceWebsite(link: String) {
    val uriHandler = LocalUriHandler.current
    val str = buildAnnotatedString {
        append("Visit their ")
        pushStringAnnotation(tag = "URL", annotation = link)
        withStyle(style = SpanStyle(color = MaterialTheme.colorScheme.primary)) {
            append("website")
        }
    }
    ClickableText(text = str, onClick = {
        str.getStringAnnotations("URL", it, it).firstOrNull()?.let {
            annotation -> uriHandler.openUri(annotation.item)
        }
    })
}

@Composable
private fun PlaceExtraDetails(hours: String, price: String) {
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
            Text(hours, fontWeight = FontWeight.Bold)
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

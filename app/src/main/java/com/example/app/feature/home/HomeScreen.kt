package com.example.app.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.lerp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import coil.compose.AsyncImage
import com.example.app.components.ImageCard
import com.example.app.model.City
import com.example.app.model.Place
import com.example.app.ui.theme.Typography
import kotlin.math.absoluteValue

const val HOME_ROUTE = "home"

fun NavGraphBuilder.homeScreen(
    onPlaceClick: (String) -> Unit,
    onCityClick: (String) -> Unit
) {
    composable(route = HOME_ROUTE) {
        HomeRoute(
            onPlaceClick = onPlaceClick,
            onCityClick = onCityClick
        )
    }
}

@Composable
private fun HomeRoute(
    onPlaceClick: (String) -> Unit,
    onCityClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val randomPlaces by viewModel.randomPlaces.collectAsStateWithLifecycle()
    val savedPlaces by viewModel.savedPlaces.collectAsStateWithLifecycle()
    val savedCities by viewModel.savedCities.collectAsStateWithLifecycle()
    HomeScreen(
        randomPlaces = randomPlaces,
        savedPlaces = savedPlaces,
        savedCities = savedCities,
        onPlaceClick = onPlaceClick,
        onCityClick = onCityClick
    )
}

@Composable
private fun HomeScreen(
    randomPlaces: List<Place>,
    savedPlaces: List<Place>,
    savedCities: List<City>,
    onPlaceClick: (String) -> Unit,
    onCityClick: (String) -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        HomeHeading()
        Spacer(modifier = Modifier.height(8.dp))
        RandomPlacesPager(places = randomPlaces, onPlaceClick = onPlaceClick)
        Spacer(modifier = Modifier.height(8.dp))

        Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                "You can view some of you saved places and cities here, a more complete view is shown in the views tab.",
                style = MaterialTheme.typography.bodyMedium,
                textAlign = TextAlign.Center
            )
        }

        if (savedPlaces.isNotEmpty()) {
            Column(modifier = Modifier.padding(12.dp).fillMaxWidth()) {
                Text("Saved Places", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    savedPlaces.shuffled().take(4).forEach {
                        ImageCard(it.image, onClick = { onPlaceClick(it.name) })
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(12.dp))

        if (savedCities.isNotEmpty()) {
            Column(modifier = Modifier.padding(12.dp)) {
                Text("Saved Cities", style = MaterialTheme.typography.headlineMedium)
                Spacer(modifier = Modifier.height(4.dp))
                Row(modifier = Modifier.horizontalScroll(rememberScrollState())) {
                    savedCities.shuffled().take(4).forEach {
                        ImageCard(it.image, onClick = { onCityClick(it.name) })
                    }
                }
            }
        }
    }
}

@Composable
private fun HomeHeading() {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            "Tour the Garden State",
            style = Typography.headlineMedium,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 24.dp)
        )
        Text(
            "View places in the Places tab or cities in the Cities tab. Click on a place or city to view its details or save it. Check some of these places out below",
            style = Typography.bodyMedium,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(12.dp, 8.dp)
        )
    }
}

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterial3Api::class)
@Composable
private fun RandomPlacesPager(places: List<Place>, onPlaceClick: (String) -> Unit) {
    val pagerState = rememberPagerState(initialPage = 2, pageCount = { places.size })
    HorizontalPager(
        state = pagerState,
        contentPadding = PaddingValues(horizontal = 80.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) { page ->
        if (page == pagerState.currentPage) {
            Card(
                modifier = Modifier.height(240.dp).fillMaxHeight(),
                colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
                onClick = { onPlaceClick(places[page].name) },
            ) {
                Column(
                    modifier = Modifier.fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ImageHeader(places[page].image)
                    Text(
                        places[page].name,
                        modifier = Modifier.padding(12.dp),
                        style = MaterialTheme.typography.titleMedium,
                        textAlign = TextAlign.Center,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis

                    )
                }
            }
        } else {
            ImageHeader(
                places[page].image,
                Modifier.graphicsLayer {
                    val pageOffset =
                        ((pagerState.currentPage - page) + pagerState.currentPageOffsetFraction).absoluteValue
                    alpha = lerp(
                        start = 0.5f,
                        stop = 1.0f,
                        fraction = 1f - pageOffset.coerceIn(0f, 1f)
                    )
                }
            )
        }
    }
}

@Composable
private fun ImageHeader(imageUrl: String, modifier: Modifier = Modifier) {
    Card(modifier = Modifier.padding(12.dp).height(160.dp).fillMaxHeight()) {
        AsyncImage(
            model = imageUrl,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            modifier = modifier
        )
    }
}

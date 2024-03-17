package com.example.app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.example.app.ui.PlaceCard


const val SEARCH_ROUTE = "search"

@Composable
private fun SearchScreen(
    onPlaceClick: (String) -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    Column {
        SearchField( query ) { viewModel.onQueryChanged(it) }
        LazyColumn {
            items(searchResults) { place ->
                PlaceCard(place, { onPlaceClick(place.name) } )
            }
        }
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChanged: (String) -> Unit,
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent
        ),
        value = query,
        onValueChange = { onQueryChanged(it) },
        shape = RoundedCornerShape(32.dp),
        leadingIcon = { Icon(
            imageVector = Icons.Default.Search,
            contentDescription = ""
        ) },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier
            .focusRequester(focusRequester)
            .fillMaxWidth()
            .padding(16.dp)
    )
}

fun NavGraphBuilder.searchScreen(onPlaceClick: (String) -> Unit) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(onPlaceClick)
    }
}

fun NavController.navigateToSearch() {
    navigate(SEARCH_ROUTE)
}



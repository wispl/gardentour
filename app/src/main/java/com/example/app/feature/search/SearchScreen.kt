package com.example.app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
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
import com.example.app.components.PlaceCardsList

const val SEARCH_ROUTE = "search"

fun NavGraphBuilder.searchScreen(
    onPlaceClick: (String) -> Unit,
    onBackClick: () -> Unit
) {
    composable(route = SEARCH_ROUTE) {
        SearchScreen(
            onPlaceClick = onPlaceClick,
            onBackClick = onBackClick
        )
    }
}

fun NavController.navigateToSearch() {
    navigate(SEARCH_ROUTE)
}

@Composable
private fun SearchScreen(
    onPlaceClick: (String) -> Unit,
    onBackClick: () -> Unit,
    viewModel: SearchViewModel = hiltViewModel()
) {
    val query by viewModel.searchQuery.collectAsStateWithLifecycle()
    val searchResults by viewModel.searchResults.collectAsStateWithLifecycle()

    Column {
        SearchToolbar(
            query,
            onQueryChanged = { viewModel.onQueryChanged(it) },
            onBackClick = onBackClick
        )
        PlaceCardsList(searchResults, onPlaceClick)
    }
}

@Composable
private fun SearchToolbar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onBackClick: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        IconButton(onClick = onBackClick) {
            Icon(
                imageVector = Icons.Rounded.ArrowBack,
                contentDescription = ""
            )
        }

        SearchField( query ) { onQueryChanged(it) }
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

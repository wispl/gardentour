package com.example.app.feature.search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable


const val SEARCH_ROUTE = "search"

@Composable
private fun SearchScreen(viewModel: SearchViewModel = viewModel()) {
    val query by viewModel.searchQuery.collectAsState()
    val searchResults by viewModel.searchResults.collectAsState()

    Column {
        SearchField(
            query,
            { viewModel.onQueryChanged(it) },
            { viewModel.onSearchTriggered(it) }
        )
    }
}

@Composable
private fun SearchField(
    query: String,
    onQueryChanged: (String) -> Unit,
    onSearchTriggered: (String) -> Unit
) {
    val focusRequester = remember { FocusRequester() }

    TextField(
        value = query,
        onValueChange = { if ("\n" !in it) onQueryChanged(it) },
        shape = RoundedCornerShape(16.dp),
        leadingIcon = { Icons.Default.Search },
        maxLines = 1,
        singleLine = true,
        modifier = Modifier.focusRequester(focusRequester)
    )
}

fun NavGraphBuilder.searchScreen() {
    composable(route = SEARCH_ROUTE) {
        SearchScreen()
    }
}



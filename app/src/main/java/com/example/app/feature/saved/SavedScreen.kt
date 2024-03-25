package com.example.app.feature.saved

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val SAVED_ROUTE = "saved"

fun NavGraphBuilder.savedScreen() {
    composable(route = SAVED_ROUTE) {
        SavedScreen()
    }
}

@Composable
fun SavedScreen() {
    var tab by remember { mutableIntStateOf(0) }

    val tabs = listOf("Places", "Cities")
    Column(modifier = Modifier.fillMaxWidth()) {
        TabRow(selectedTabIndex = tab) {
            tabs.forEachIndexed() { index, title ->
                Tab(
                    content = { Text(title) },
                    selected = (tab == index),
                    onClick = { tab = index }
                )
            }
        }
    }
}

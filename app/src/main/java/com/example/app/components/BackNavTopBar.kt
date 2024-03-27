package com.example.app.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BackNavTopBar(
    text: String,
    imageVector: ImageVector,
    onBackClick: () -> Unit,
    onActionClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(text) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Rounded.ArrowBack,
                    contentDescription = "Navigate back"
                )
            }
        },
        actions = {
            IconButton(onClick = onActionClick) {
                Icon(
                    imageVector = imageVector,
                    contentDescription = "Save"
                )
            }
        }
    )
}
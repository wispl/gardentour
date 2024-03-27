package com.example.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

/* Small clickable card with image card */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ImageCard(
    imageUrl: String,
    onClick: () -> Unit,
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
    ) {
        Card(modifier = Modifier.height(160.dp).width(200.dp).padding(8.dp)) {
            AsyncImage(
                model = imageUrl,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
        }
    }
}
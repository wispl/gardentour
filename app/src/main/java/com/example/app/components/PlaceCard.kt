package com.example.app.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.model.Place
import com.example.app.model.PlaceType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCard(
    place: Place,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.padding((12.dp))
    ) {
        Column {
            PlaceImageHeader(place.image, place.imageCredit)
            Box(modifier = Modifier.padding(18.dp)) {
                Column {
                    PlaceDescription(place.name, place.description)
                    PlaceTypes(place.types)
                }
            }
        }
    }
}

@Composable
private fun PlaceImageHeader(image: String, description: String) {
    Box(modifier = Modifier.height(180.dp).fillMaxWidth()) {
        AsyncImage(
            model = image,
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth().height(180.dp)
        )
    }
}

@Composable
private fun PlaceDescription(name: String, description: String) {
    Text(text = name, style = typography.headlineSmall)
    Spacer(Modifier.height(8.dp))
    Text(
        text = description,
        style = typography.bodyMedium,
        maxLines = 4,
        overflow = TextOverflow.Ellipsis
    )
}

@Composable
fun PlaceTypes(types: Set<PlaceType>) {
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach {
            Tag(enabled = true) {
                Text(it.name)
            }
        }
    }
}
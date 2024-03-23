package com.example.app.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.app.model.City

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityCard(
    city: City,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        modifier = modifier.padding((12.dp))
    ) {
        Row {
            CityImage(city.image, city.imageCredit)
            CityBody(city.name, city.location, city.description)
        }
    }
}

@Composable
private fun CityImage(image: String, description: String) {
    Card(modifier = Modifier.height(160.dp).width(120.dp)) {
        AsyncImage(
            model = image,
            contentDescription = description,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Composable
private fun CityBody(
    name: String,
    location: String,
    description: String
) {
    Box(modifier = Modifier.padding(18.dp)) {
        Column(verticalArrangement = Arrangement.Center) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(text = name, style = typography.headlineSmall)
                Spacer(modifier = Modifier.height(4.dp))
                Tag(enabled = true) {
                    Text(location)
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = description,
                style = typography.bodyLarge,
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}
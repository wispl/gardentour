package com.example.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.app.data.PlaceData
import com.example.app.data.PlaceType

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PlaceCard(
    place: PlaceData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(onClick = onClick, modifier = modifier.padding((12.dp))) {
        Column {
            PlaceImageHeader(place.image)
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
private fun PlaceImageHeader(image: Int) {
    Box(modifier = Modifier.height(180.dp).fillMaxWidth()) {
        Image(
            painter = painterResource(image),
            contentDescription = "",
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
    val containerColor = MaterialTheme.colorScheme.primaryContainer
    Row(
        modifier = Modifier.horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        types.forEach {
            TextButton(
                onClick = {},
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = containerColor,
                    contentColor = contentColorFor(backgroundColor = containerColor)
                ),
                shape = RoundedCornerShape(8.dp)
            ) {
               Text(it.name)
            }
        }
    }
}
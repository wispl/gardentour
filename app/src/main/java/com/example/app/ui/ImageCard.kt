package com.example.app.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp

@Composable
fun ImageCard(title: String, image: Painter, description: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier.padding((12.dp))) {
        Column(modifier = modifier.padding(12.dp)) {
            Card(modifier = Modifier.height(150.dp).fillMaxWidth()) {
                Image(
                    painter = image,
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Text(text = title, style = typography.titleMedium, modifier = Modifier.padding(4.dp))
            Text(text = description, style = typography.bodyMedium, maxLines =  4, overflow = TextOverflow.Ellipsis)
        }
    }
}

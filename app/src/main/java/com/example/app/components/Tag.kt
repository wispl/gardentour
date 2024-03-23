package com.example.app.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TextButton
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Tag(
    modifier: Modifier = Modifier,
    enabled: Boolean,
    text: @Composable () -> Unit
) {
    val containerColor = if (enabled) {
        MaterialTheme.colorScheme.primaryContainer
    } else {
        MaterialTheme.colorScheme.surfaceVariant.copy(
            alpha = 0.5f
        )
    }

    Box(modifier = modifier) {
        TextButton(
            onClick = {},
            enabled = enabled,
            colors = ButtonDefaults.buttonColors(
                containerColor = containerColor,
                contentColor = contentColorFor(backgroundColor = containerColor),
            ),
            shape = RoundedCornerShape(8.dp)
        ) {
            text()
        }

    }
}
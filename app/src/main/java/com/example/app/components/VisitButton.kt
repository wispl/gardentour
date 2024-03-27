package com.example.app.components

import android.content.Context
import android.net.Uri
import androidx.browser.customtabs.CustomTabColorSchemeParams
import androidx.browser.customtabs.CustomTabsIntent
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
fun VisitButton(
    modifier: Modifier = Modifier,
    url: String,
    text: @Composable () -> Unit
)
 {
    val context = LocalContext.current
    val color = MaterialTheme.colorScheme.background.toArgb()
    Button(
        content = { text() },
        onClick = { launchChromeTab(context, Uri.parse(url), color) },
        modifier = modifier
    )
}

fun launchChromeTab(context: Context, uri: Uri, color: Int ) {
    val tabColor = CustomTabColorSchemeParams.Builder()
        .setToolbarColor(color).build()
    val tabsIntent = CustomTabsIntent.Builder()
        .setDefaultColorSchemeParams(tabColor)
        .build()

    tabsIntent.launchUrl(context, uri)
}
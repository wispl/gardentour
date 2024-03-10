package com.example.app

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.feature.home.HOME_ROUTE
import com.example.app.feature.places.PLACES_ROUTE
import com.example.app.feature.search.navigateToSearch
import com.example.app.navigation.AppDestination
import com.example.app.navigation.AppNavHost
import com.example.app.navigation.navigateToDestination

@Composable
fun AppBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val current = backStackEntry?.destination

    NavigationBar(modifier = modifier) {
        AppDestination.entries.forEach {
            NavigationBarItem(
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                label = { Text(stringResource(it.text)) },
                selected = (current.isSelected(it)),
                onClick = { navController.navigateToDestination(it) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppTopBar(
    title: Int,
    navIcon: ImageVector,
    modifier: Modifier = Modifier,
    onNavigationClick: () -> Unit,
) {
    CenterAlignedTopAppBar(
        title = { Text(stringResource(title)) },
        navigationIcon = {
            IconButton(onClick = onNavigationClick) {
                Icon(
                    imageVector = navIcon,
                    contentDescription = ""
                )
            }
        },
        modifier = modifier
    )
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val current = backStackEntry?.destination
    val destination = when(current?.route) {
        HOME_ROUTE -> AppDestination.Home
        PLACES_ROUTE -> AppDestination.Places
        else -> null
    }

    Scaffold(bottomBar = { AppBottomBar(navController) }) { padding ->
        Row(
            Modifier
                .fillMaxSize()
                .padding(padding)
                .consumeWindowInsets(padding)
                .windowInsetsPadding(
                    WindowInsets.safeDrawing.only(
                        WindowInsetsSides.Horizontal
                    )
                )
        ) {
            Column(Modifier.fillMaxSize()) {
                if (destination != null) {
                    AppTopBar(
                        destination.title,
                        Icons.Rounded.Search,
                        onNavigationClick = { navController.navigateToSearch() }
                    )
                }

                AppNavHost(navController)
            }
        }
    }
}

private fun NavDestination?.isSelected(destination: AppDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
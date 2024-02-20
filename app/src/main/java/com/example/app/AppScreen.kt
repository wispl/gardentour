package com.example.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.app.ui.HomeScreen
import com.example.app.ui.PlacesScreen

enum class ScreenType(val title: String, val icon: ImageVector) {
    Home(title = "Home", icon = Icons.Default.Home),
    Places(title = "Places", icon = Icons.Default.Place),
    Search(title = "Search", icon = Icons.Default.Search),
    Saved(title = "Saved", icon = Icons.Default.FavoriteBorder)
}

@Composable
fun AppBottomBar(navController: NavHostController, modifier: Modifier = Modifier) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val screen = ScreenType.valueOf(backStackEntry?.destination?.route ?: ScreenType.Home.name)

    NavigationBar(modifier = modifier) {
        ScreenType.entries.forEach {
            NavigationBarItem(
                icon = { Icon(imageVector = it.icon, contentDescription = null) },
                label = { it.title},
                selected = (screen == it),
                onClick = { navController.navigate(it.name) },
            )
        }
    }
}

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val screen = ScreenType.valueOf(backStackEntry?.destination?.route ?: ScreenType.Home.name)

    Scaffold(
        bottomBar = { AppBottomBar(navController) },
    ) {
        innerPadding ->
        NavHost(
            navController = navController,
            startDestination = ScreenType.Home.name,
            modifier = Modifier.fillMaxSize().padding(innerPadding))
        {
            composable(route = ScreenType.Home.name) { HomeScreen(Modifier) }
            composable(route = ScreenType.Places.name) { PlacesScreen(Modifier) }
        }
    }
}
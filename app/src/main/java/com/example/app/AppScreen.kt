package com.example.app

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
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
                label = { it.text},
                selected = (current.isSelected(it)),
                onClick = { navController.navigateToDestination(it) },
            )
        }
    }
}

@Composable
fun AppScreen(navController: NavHostController = rememberNavController()) {
    Scaffold(
        bottomBar = { AppBottomBar(navController) },
    ) {
        innerPadding -> AppNavHost(navController, modifier = Modifier.fillMaxSize().padding(innerPadding))
    }
}

private fun NavDestination?.isSelected(destination: AppDestination) =
    this?.hierarchy?.any {
        it.route?.contains(destination.name, true) ?: false
    } ?: false
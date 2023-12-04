package com.example.gestiondemusica.presentation.view.scaffolds.scaffold

import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.gestiondemusica.presentation.nav.AppScreen

@Composable
fun ScaBottomBar(navController: NavController, modifier: Modifier = Modifier) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    NavigationBar(
        modifier = Modifier.fillMaxWidth().height(45.dp),
        tonalElevation = 3.0.dp,
    ) {
        NavBarItem(
            route = AppScreen.HomeScreen.route,
            navController = navController,
            imageVector = Icons.Default.Home,
            contentDescription = null,
            rowScope = this,
            currentDestination = currentDestination,
        )
        NavBarItem(
            route = AppScreen.ListSongsScreen.route,
            navController = navController,
            imageVector = Icons.Default.List,
            contentDescription = null,
            rowScope = this,
            currentDestination = currentDestination,
        )
        NavBarItem(
            route = AppScreen.DetailsScreen.route,
            navController = navController,
            imageVector = Icons.Default.Favorite,
            contentDescription = null,
            rowScope = this,
            currentDestination = currentDestination,
        )
        NavBarItem(
            route = AppScreen.SettingsScreen.route,
            navController = navController,
            imageVector = Icons.Default.Settings,
            contentDescription = null,
            rowScope = this,
            currentDestination = currentDestination,
        )
    }
}

@Composable
fun NavBarItem(
    route: String,
    navController: NavController,
    imageVector: ImageVector,
    contentDescription: String?,
    rowScope: RowScope,
    currentDestination: NavDestination?,
) {
    rowScope.NavigationBarItem(
        selected = currentDestination?.hierarchy?.any { it.route == route } == true,
        onClick = { navController.navigate(route = route) },
        icon = { Icon(imageVector = imageVector, contentDescription = contentDescription) },
    )
}
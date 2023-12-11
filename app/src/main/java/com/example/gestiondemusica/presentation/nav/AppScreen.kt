package com.example.gestiondemusica.presentation.nav

sealed class AppScreen(val route:String) {
    object HomeScreen: AppScreen("HomeScreen")
    object ListSongsScreen: AppScreen("ListSongsScreen")
    object DetailsScreen: AppScreen("DetailsScreen")
    object SettingsScreen: AppScreen("SettingsScreen")
    object SearchScreen: AppScreen("SearchScreen")
}
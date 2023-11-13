package com.example.gestiondemusica.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.bottomsheetplayer.BottomPlayerBar
import com.example.gestiondemusica.presentation.view.scaffold.AppScaffold
import com.example.gestiondemusica.presentation.view.screens.details.DetailsScreen
import com.example.gestiondemusica.presentation.view.screens.home.HomeScreen
import com.example.gestiondemusica.presentation.view.screens.lists.ListSongsScreen
import com.example.gestiondemusica.presentation.view.screens.settings.SettingsScreen

@Composable
fun AppNavigation(musicVM: MusicViewModel, reproVM: ReproductorViewModel, userVM: UserViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.HomeScreen.route) {

        composable(route = AppScreen.HomeScreen.route) {
            AppScaffold(navController) {
                BottomPlayerBar(musicVM, reproVM, userVM) {
                    HomeScreen(navController, musicVM, reproVM, userVM)
                }
            }
        }

        composable(route = AppScreen.ListSongsScreen.route) {
            AppScaffold(navController) {
                BottomPlayerBar(musicVM, reproVM, userVM) {
                    ListSongsScreen(navController, musicVM, reproVM, userVM)
                }
            }
        }

        composable(route = AppScreen.DetailsScreen.route) {
            AppScaffold(navController) {
                BottomPlayerBar(musicVM, reproVM, userVM) {
                    DetailsScreen(navController, musicVM, reproVM, userVM)
                }
            }
        }

        composable(route = AppScreen.SettingsScreen.route) {
            AppScaffold(navController) {
                BottomPlayerBar(musicVM, reproVM, userVM) {
                    SettingsScreen(navController, musicVM, reproVM, userVM)
                }
            }
        }

    }

}
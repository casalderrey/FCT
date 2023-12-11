package com.example.gestiondemusica.presentation.nav

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold.BottomSheetM2
import com.example.gestiondemusica.presentation.view.scaffolds.scaffold.ScaffoldApp
import com.example.gestiondemusica.presentation.view.screens.favorite.DetailsScreen
import com.example.gestiondemusica.presentation.view.screens.home.HomeScreen
import com.example.gestiondemusica.presentation.view.screens.lists.ListSongsScreen
import com.example.gestiondemusica.presentation.view.screens.search.SearchScreen
import com.example.gestiondemusica.presentation.view.screens.settings.SettingsScreen

@Composable
fun AppNavigation(musicVM: MusicViewModel, reproVM: ReproductorViewModel, userVM: UserViewModel) {

    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppScreen.HomeScreen.route) {

        composable(route = AppScreen.HomeScreen.route) {
            ScaffoldApp(navController) { pvSc ->
                BottomSheetM2(musicVM, reproVM, userVM, pvSc) { pvBS ->
                    HomeScreen(musicVM, reproVM, userVM, pvBS)
                }
            }
        }

        composable(route = AppScreen.ListSongsScreen.route) {
            ScaffoldApp(navController) { pvSc ->
                BottomSheetM2(musicVM, reproVM, userVM, pvSc) { pvBS ->
                    ListSongsScreen(musicVM, reproVM, userVM, pvBS)
                }
            }
        }

        composable(route = AppScreen.DetailsScreen.route) {
            ScaffoldApp(navController) { pvSc ->
                BottomSheetM2(musicVM, reproVM, userVM, pvSc) { pvBS ->
                    DetailsScreen( reproVM, userVM, pvBS)
                }
            }
        }

        composable(route = AppScreen.SettingsScreen.route) {
            ScaffoldApp(navController) { pvSc ->
                BottomSheetM2(musicVM, reproVM, userVM, pvSc) { pvBS ->
                    SettingsScreen(musicVM,  userVM,  pvBS)
                }
            }
        }

        composable(route = AppScreen.SearchScreen.route) {
            ScaffoldApp(navController) { pvSc ->
                BottomSheetM2(musicVM, reproVM, userVM, pvSc) { pvBS ->
                    SearchScreen(navController, musicVM, reproVM)
                }
            }
        }

    }

}
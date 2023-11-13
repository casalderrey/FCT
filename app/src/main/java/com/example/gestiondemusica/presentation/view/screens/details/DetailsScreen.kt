package com.example.gestiondemusica.presentation.view.screens.details

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun DetailsScreen(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userViewModel: UserViewModel,
) {
    Text(text = "Details")
}
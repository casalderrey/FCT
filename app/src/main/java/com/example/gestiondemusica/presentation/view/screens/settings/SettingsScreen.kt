package com.example.gestiondemusica.presentation.view.screens.settings

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun SettingsScreen(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userViewModel: UserViewModel,
) {

    Row(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Cambio de tema ")
        Switch(checked = userViewModel.darkTheme, onCheckedChange = {userViewModel.setTheme(!userViewModel.darkTheme)})
    }

}
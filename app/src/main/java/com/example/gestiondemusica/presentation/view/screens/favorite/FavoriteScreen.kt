package com.example.gestiondemusica.presentation.view.screens.favorite

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens.lists.ListSongList

@Composable
fun DetailsScreen(
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    paddingValues: PaddingValues,
) {

    if(userVM.userSongList.keys.contains("Favorite")){
        ListSongList(songList = userVM.userSongList.getValue("Favorite"), reproVM = reproVM)
    }else{
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Todavía no hay canciones añadidas a favoritos")
        }
    }

}
package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens.home.HomeMostListenedAlbum
import com.example.gestiondemusica.presentation.view.screens.home.HomeMostListenedSong
import com.example.gestiondemusica.presentation.view.screens.home.HomeRandomAlbum
import com.example.gestiondemusica.presentation.view.screens.home.HomeRandomSong

@Composable
fun HomeScreen(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    paddingValues: PaddingValues,
) {

    if(musicVM.listSongs.isEmpty()) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(text = "Todavía no hay canciones en tu biblioteca\nAgregalas en Ajustes")
        }

    }else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(horizontal = 5.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp),
            horizontalAlignment = Alignment.Start,
        ) {
            item {
                HomeRandomSong(musicVM, reproVM, userVM)
                HomeRandomAlbum(musicVM, reproVM, userVM)
                HomeMostListenedSong(musicVM, reproVM, userVM)
                HomeMostListenedAlbum(musicVM, reproVM, userVM)
            }
        }
    }
}

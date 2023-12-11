package com.example.gestiondemusica.presentation.view.screens.lists

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.UIState
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun ListSongsScreen(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    paddingValues: PaddingValues,
) {

    var uiState = musicVM.uiState.collectAsState()
    if(musicVM.listSongs.isEmpty()) {
//      musicVM.getMusicListFromUri()
        musicVM.getMusicListFromStringDirectory()
        if (uiState.value == UIState.LOADING) {
            CircularProgressIndicator()
        }
    }else {
        musicVM.listSongs.map {
            Log.d("DebugTag", "Debug on: MainActivity.onCreate,\n  $it")
            if (it != null) {
                userVM.addSongToList(key = "Todas", song = it)
            }
        }

        userVM.createList(key="Artista")
        userVM.createList(key="Género")
        //userVM.createList(key="Mis listas")

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            TabScaffold(
                musicVM = musicVM,
                userVM = userVM,
                reproVM = reproVM,
            )

        }
    }




}
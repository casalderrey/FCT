package com.example.gestiondemusica

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.core.splashscreen.SplashScreen
import com.example.gestiondemusica.presentation.state.music.MusicViewModel

@Composable
fun Splash(splashScreen: SplashScreen, musicVM: MusicViewModel) {

    musicVM.getMusicListFromStringDirectory()
    val musicState = musicVM.uiState.collectAsState()

    splashScreen.setKeepOnScreenCondition {
        false //musicState.value != UIState.SUCCESSFUL || timer > 0
    }

}
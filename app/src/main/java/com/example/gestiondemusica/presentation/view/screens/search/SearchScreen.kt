package com.example.gestiondemusica.presentation.view.screens.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel

@Composable
fun SearchScreen(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
) {
    var query by rememberSaveable() { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        SearchBarContent(musicVM = musicVM, reproVM = reproVM, navController = navController, query = query,  queryOnChange = { query=it })
        SearchContent(musicVM = musicVM, reproVM = reproVM, navController = navController, query = query)
    }

}
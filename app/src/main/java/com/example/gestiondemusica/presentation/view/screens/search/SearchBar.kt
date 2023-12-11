package com.example.gestiondemusica.presentation.view.screens.search

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel

@Composable
fun SearchBarContent(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    navController: NavController,
    query: String,
    queryOnChange: (String) -> Unit,
) {

    var active by rememberSaveable() { mutableStateOf(false) }
    SearchBarHeader(navController, musicVM, reproVM, query, queryOnChange, active, { active=it })

}
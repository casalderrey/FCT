package com.example.gestiondemusica.presentation.view.scaffold

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController

@Composable
fun AppScaffold(navController: NavController, Contenido: @Composable () -> Unit) {
    Scaffold(
        topBar = { ScaTopBar(navController) },
        bottomBar = { ScaBottomBar(navController) },
    ) {
        Surface(
            modifier = Modifier.padding(it).fillMaxSize()
        ) {
            Contenido()
        }
    }
}
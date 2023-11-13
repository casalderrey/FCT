package com.example.gestiondemusica.presentation.view.scaffold

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController

// Barra Superior
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaTopBar(navController: NavController) {
    Surface(tonalElevation = 1.5.dp, shadowElevation = 6.0.dp, ) {
        TopAppBar(
            modifier = Modifier.height(45.dp),
            title = { Text(text = "HOME") },
            actions = {
                IconButton(modifier = Modifier.size(24.dp), onClick = { /*TODO*/}) {
                    Icon(Icons.Filled.Search, "back icon")
                }
            },
            //windowInsets = WindowInsets(2.dp, 15.dp,10.dp, 7.dp)
        )
    }
}
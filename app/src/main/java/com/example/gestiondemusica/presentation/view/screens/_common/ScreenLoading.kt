package com.example.gestiondemusica.presentation.view.screens._common

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun ScreenLoading() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        Text(text = "Loading...")
    }
}
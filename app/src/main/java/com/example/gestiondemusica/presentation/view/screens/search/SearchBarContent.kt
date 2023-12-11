package com.example.gestiondemusica.presentation.view.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong

@Composable
fun SearchBarContent(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    query: String,
) {
    if (query.isNotEmpty()) {
        musicVM.findSong(query)
        SearchBarContentBody(
            itemsList = musicVM.searchList.sortedBy { it.artistSong },
            reproVM = reproVM,
            navController = navController,
        )
    } else {
        Text(text = "No se ha encontrado ninguna canción")
    }
}

@Composable
fun SearchBarContentBody(
    itemsList: List<Song>,
    reproVM: ReproductorViewModel,
    navController: NavController,
) {
    LazyColumn() {
        items(items = itemsList) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxWidth()
                    .clickable {
                        reproVM.setSongList(it)
                        reproVM.setPlayerList()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageSong(
                    image = it.image,
                    modifier = Modifier
                        .width(20.dp)
                        .height(30.dp)
                )
                Text(text = it.titleSong ?: "")
            }
        }
    }
}
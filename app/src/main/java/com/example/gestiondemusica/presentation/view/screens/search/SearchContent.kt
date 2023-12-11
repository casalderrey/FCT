package com.example.gestiondemusica.presentation.view.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong

@Composable
fun SearchContent(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    navController: NavController,
    query: String,
) {
    if (query.isNotEmpty()) {
        musicVM.findSong(query)
        SearchContentBody(musicVM.searchList, reproVM, navController)

    }else {
        Text(text = "No se ha encontrado canciones")
    }

}


@Composable
fun SearchContentBody(
    itemsList: List<Song>,
    reproductorVM: ReproductorViewModel,
    navController: NavController,
) {
    LazyVerticalGrid(columns = GridCells.Fixed(3), contentPadding = PaddingValues(4.dp)){
        items(items = itemsList) {
            Row(
                modifier = Modifier
                    .padding(2.dp)
                    .fillMaxSize()
                    .clickable {
                        reproductorVM.setSongList(it)
                        reproductorVM.setPlayerList()
                    },
                verticalAlignment = Alignment.CenterVertically
            ) {
                ImageSong(
                    image = it.image,
                )
                Text(
                    text = "${it.titleSong}",
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .height(height = 52.dp)
                        .align(Alignment.CenterVertically),
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
        }
    }
}
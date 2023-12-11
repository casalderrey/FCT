package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.UIState
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ShimmerListItem
import com.example.gestiondemusica.utils.extension.getRandomItems

@Composable
fun HomeRandomSong(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    val uiState by musicVM.uiState.collectAsState()
    var loading by remember { mutableStateOf(false) }

    Spacer(modifier = Modifier.height(8.dp))
    Text(
        modifier = modifier,
        text = "Canción aleatoria: ",
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )

    val listRandomSong = musicVM.listSongs
        .filterNotNull()
        .getRandomItems(20)

    if(uiState != UIState.SUCCESSFUL) {

        LazyHorizontalGrid(
            modifier = Modifier.height(300.dp),
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(20) { song ->
                ShimmerListItem(
                    isLoading = uiState != UIState.SUCCESSFUL,
                    contentAfterLoading = { },
                )
            }
        }

    }else {

        LazyHorizontalGrid(
            modifier = Modifier.height(300.dp),
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(items = listRandomSong) { song ->
                HomeSongsItem(song, musicVM, reproVM, userVM)
            }
        }

    }

}
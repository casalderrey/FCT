package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun HomeMostListenedSong(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {

    val listMostListen = musicVM.listSongs
        .filterNotNull()
        .sortedByDescending { it.countListen }
        .filter { it.countListen > 0 }
        .take(20)

    if (listMostListen.isNotEmpty()) {

        Spacer(modifier = Modifier.height(9.dp))
        Text(
            modifier = modifier,
            text = "Canciones más escuchadas: ",
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(3.dp))

        LazyHorizontalGrid(
            modifier = Modifier.height(300.dp),
            rows = GridCells.Fixed(2),
            contentPadding = PaddingValues(1.dp),
        ) {
            items(items = listMostListen) { song ->
                HomeSongsItem(song, musicVM, reproVM, userVM)
            }
        }

    }

}


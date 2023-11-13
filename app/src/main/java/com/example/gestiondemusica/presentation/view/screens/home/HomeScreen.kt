package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondemusica.R
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun HomeScreen(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyHorizontalGrid(rows = GridCells.Fixed(2), contentPadding = PaddingValues(4.dp)) {
            items(items = musicVM.listSongs, key = { it.song_Url }) {
                ItemSongCardHome(userVM, reproVM, it)
            }
        }

    }

}

@Composable
fun ItemSongCardHome(
    userVM: UserViewModel,
    reproVM: ReproductorViewModel,
    song: Song,
) {

    var isFavorite by rememberSaveable { mutableStateOf(userVM.isSongOnList(song = song)) }

    Card(
        modifier = Modifier
            .padding(10.dp)
            .aspectRatio(1f)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds,
                bitmap = if (song.image != null) {
                    song.image!!.asImageBitmap()
                } else {
                    ImageBitmap.imageResource(R.drawable.logo1)
                },
                contentDescription = null
            )

            IconButton(
                onClick = {
                    if (userVM.isSongOnList(song = song)) {
                        userVM.removeSongToList(song = song)
                        isFavorite = false
                    }else {
                        userVM.addSongToList(song = song)
                        isFavorite = true
                    }
                },
                modifier = Modifier.padding(bottom = 8.dp)
            ) {
                if (isFavorite){
                    Icon(imageVector = Icons.Outlined.Favorite, contentDescription = null)
                }else{
                    Icon(imageVector = Icons.TwoTone.Favorite, contentDescription = null)
                }
            }

        }
    }

}
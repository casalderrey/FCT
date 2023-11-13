package com.example.gestiondemusica.presentation.view.screens.lists

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.res.imageResource
import com.example.gestiondemusica.R
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.utils.formatDuration

@Composable
fun ItemSongsCardList(
    reproVM: ReproductorViewModel,
    song: Song,
) {

    Card(modifier = Modifier.clickable { reproVM.setSong(song);reproVM.setPlayer() }) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Image(
                modifier = Modifier
                    .aspectRatio(1f / 1f)
                    .weight(1f),
                bitmap = if (song.image != null) {
                    song.image.asImageBitmap()
                } else {
                    ImageBitmap.imageResource(R.drawable.logo)
                },
                contentDescription = null
            )

            Column(modifier = Modifier.weight(1f)) {
                Text(text = "Nombre de la canción: ${song.title_song}")
                Text(text = "Artista: ${song.artist_song}")
                Text(text = "Album: ${song.title_album}")
                Text(text = "Duracion: ${formatDuration(song.duration.toLong())}")
            }

        }
    }

}
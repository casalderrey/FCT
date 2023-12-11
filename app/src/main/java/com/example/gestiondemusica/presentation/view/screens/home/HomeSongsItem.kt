package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong

@Composable
fun HomeSongsItem(
    song: Song,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier
            .width(110.dp)
            .padding(5.dp)
            .clickable { reproVM.setSongList(song); reproVM.setPlayerList() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Box(contentAlignment = Alignment.CenterStart) {
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .border(BorderStroke(2.dp, Color.Red)),
            )

            ImageSong(
                image = song.image,
                modifier = Modifier.size(100.dp).clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Text(
            text = song.titleSong ?: "Undefined",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

        Text(
            text = song.artistSong ?: "Undefined",
            fontSize = 11.sp,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )

    }

}

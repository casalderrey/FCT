package com.example.gestiondemusica.presentation.view.screens.lists

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Alignment.Companion.End
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestiondemusica.R
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong

@Composable
fun ListSongsItemCard(
    reproVM: ReproductorViewModel,
    song: Song,
) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                reproVM.setSongList(song)
                reproVM.setPlayerList()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {

        Box(contentAlignment = Alignment.CenterStart) {
            Box(
                modifier = Modifier
                    .size(45.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .border(BorderStroke(2.dp, Color.Red)),
            )

            ImageSong(
                image = song.image,
                modifier = Modifier
                    .size(50.dp)
                    .clip(RoundedCornerShape(10.dp)),
                contentScale = ContentScale.Crop
            )
        }

        Spacer(modifier = Modifier.width(10.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Text(
                text = song.titleSong ?: "Undefined",
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = song.artistSong ?: "Undefined",
                fontSize = 13.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Composable
fun ListOptionsSongsItemCard(
    reproVM: ReproductorViewModel,
    listSong: List<Song?>,
    option: String,
) {
    var showList by rememberSaveable { mutableStateOf(false) }


    Card {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { showList = !showList },
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row {
                ImageList(listSong)
                Spacer(modifier = Modifier.width(5.dp))
                TitleList(option)
            }

            IconButton(modifier = Modifier.align(CenterVertically), onClick = {
                reproVM.setSongList(listSong.filterNotNull())
                reproVM.setPlayerList()
            }) {
                Icon(
                    painter = painterResource(R.drawable.playlist_play),
                    contentDescription = "Reproducir lista"
                )
            }
        }

        if (showList) {
            Spacer(modifier = Modifier.height(10.dp))
            Log.i("InfoTag", "Entro en el if al click ${listSong.size}")
            repeat(listSong.size) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            reproVM.setSongList(listSong[it]!!)
                            reproVM.setPlayerList()
                        },
                    horizontalArrangement = Arrangement.Start,
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    ImageSong(
                        image = listSong[it]?.image,
                        modifier = Modifier
                            .size(25.dp)
                            .clip(RoundedCornerShape(5.dp)),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = listSong[it]?.titleSong ?: "Undefined",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )

                }
                Spacer(modifier = Modifier.height(10.dp))
            }
        }

    }

}

@Composable
private fun TitleList(option: String) {
    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = option,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ImageList(listSong: List<Song?>) {
    Box(contentAlignment = Alignment.CenterStart) {
        Box(
            modifier = Modifier
                .size(45.dp)
                .clip(RoundedCornerShape(10.dp))
                .border(BorderStroke(2.dp, Color.Red)),
        )

        ImageSong(
            image = listSong[0]!!.image,
            modifier = Modifier
                .size(50.dp)
                .clip(RoundedCornerShape(10.dp)),
            contentScale = ContentScale.Crop
        )
    }
}
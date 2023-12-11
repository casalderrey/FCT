package com.example.gestiondemusica.presentation.view.screens.home

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun HomeMostListenedAlbum(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {

    val songs: List<Song?> = musicVM.listSongs

    val mostListenedAlbumName:String? = songs
        .filterNotNull()
        .groupBy { it.titleAlbum }
        .maxByOrNull { (_, songs) -> songs.sumBy { it.countListen } }
        ?.key

    var mostListenedAlbumSongs: List<Song> = musicVM.listSongs
        .filter { it?.titleAlbum == mostListenedAlbumName }
        .filterNotNull()

    if (mostListenedAlbumSongs.isNotEmpty()) {
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            modifier = modifier,
            text = "Album más escuchado: ",
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(3.dp))

        HomeAlbumItem(mostListenedAlbumSongs = mostListenedAlbumSongs, musicVM = musicVM, reproVM = reproVM, userVM = userVM)

    }

}
package com.example.gestiondemusica.presentation.view.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.UIState
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ShimmerListItem
import com.example.gestiondemusica.presentation.view.screens.home.HomeAlbumItem
import com.example.gestiondemusica.utils.extension.getRandomItems

@Composable
fun HomeRandomAlbum(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    Spacer(modifier = Modifier.height(0.dp))
    Text(
        modifier = modifier,
        text = "Album random: ",
        fontWeight = FontWeight.Bold,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis
    )
    Spacer(modifier = Modifier.height(3.dp))

    val uiState by musicVM.uiState.collectAsState()
    ShimmerListItem(
        isLoading = uiState != UIState.SUCCESSFUL,
        contentAfterLoading = {
            val randomAlbumName:String? = musicVM.listSongs
                .filterNotNull()
                .getRandomItems(1)[0].titleAlbum

            var randomAlbumSongs: List<Song> = musicVM.listSongs
                .filter { it?.titleAlbum == randomAlbumName }
                .filterNotNull()

            Log.d("DebugTag", "Debug on: Compose.HomeRandomAlbum,\n  $randomAlbumName")

            HomeAlbumItem(
                mostListenedAlbumSongs = randomAlbumSongs,
                musicVM = musicVM,
                reproVM = reproVM,
                userVM = userVM
            )
        },
    )

}
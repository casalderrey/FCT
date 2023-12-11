package com.example.gestiondemusica.presentation.view.screens.lists

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel

@Composable
fun ListSongList(
    songList: List<Song>,
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(items = songList) {
                Spacer(modifier = Modifier.height(5.dp))
                ListSongsItemCard(reproVM, it)
                Spacer(modifier = Modifier.height(5.dp))
            }
        }

    }

}

@Composable
fun ListofSongList(
    optionList: Map<String?, List<Song?>>,
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {




    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(5.dp),
        ) {
            items(items = optionList.keys.toList()) {
                Spacer(modifier = Modifier.height(8.dp))
                if (it != null) {
                    ListOptionsSongsItemCard(reproVM = reproVM, option = it, listSong = optionList[it]!!)
                }
                Spacer(modifier = Modifier.height(8.dp))
            }
        }

    }
}
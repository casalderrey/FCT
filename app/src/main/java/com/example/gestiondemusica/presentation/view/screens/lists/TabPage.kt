package com.example.gestiondemusica.presentation.view.screens.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun TabPage(
    tabItems: List<String>,
    selectedTabIndex: Int,
    onSelectedTabIndex: (Int) -> Unit,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    musicVM: MusicViewModel,
    modifier: Modifier = Modifier,
) {
    val pageState = rememberPagerState { tabItems.size }

    LaunchedEffect(key1 = selectedTabIndex) {
        pageState.animateScrollToPage(selectedTabIndex)
    }
    LaunchedEffect(key1 = pageState.currentPage, pageState.isScrollInProgress) {
        if (!pageState.isScrollInProgress) {
            onSelectedTabIndex(pageState.currentPage)
        }
    }

    HorizontalPager(
        state = pageState,
        modifier = Modifier.fillMaxSize()
    ) { index ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {

            when (tabItems[index]) {
                "Género" -> {
                    ListofSongList(
                        optionList = musicVM.getAllGenres().flatMap { (key, value) ->
                            key?.split(", ")?.map { it.trim() }?.map { it to value } ?: emptyList()
                        }
                            .associateBy({ it.first }, { it.second }),
                        reproVM = reproVM
                    )
                }
                "Artista" -> {
                    ListofSongList(
                        optionList = musicVM.getAllArtist().flatMap { (key, value) ->
                            key?.split(", ")?.map { it.trim() }?.map { it to value } ?: emptyList()
                        }
                            .associateBy({ it.first }, { it.second }),
                        reproVM = reproVM
                    )
                }
                /*"Mis listas" -> {
                    ListofSongList(
                        optionList = musicVM.getAllArtist(),
                        reproVM = reproVM
                    )
                }*/
                else -> {
                    ListSongList(
                        songList = userVM.userSongList.getValue(tabItems[index]),
                        reproVM = reproVM
                    )
                }
            }

        }
    }
}
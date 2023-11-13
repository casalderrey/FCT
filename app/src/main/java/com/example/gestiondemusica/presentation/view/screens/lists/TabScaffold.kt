package com.example.gestiondemusica.presentation.view.screens.lists

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun TabScaffold(
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
) {
    var tabItems = userVM.userSongList.map { it.key }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }

    if(tabItems.isNotEmpty()){
        ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
            tabItems.forEachIndexed { index, item ->
                Tab(
                    selected = true,
                    onClick = { selectedTabIndex = index },
                    text = { Text(text = item) },
                )
            }
        }

        PageTab(
            tabItems = tabItems,
            selectedTabIndex = selectedTabIndex,
            onSelectedTabIndex = { selectedTabIndex = it },
            userVM = userVM,
            reproVM = reproVM,
        )
    }else {
        Text(text = "No hay listas.")
    }


}


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PageTab(
    tabItems: List<String>,
    selectedTabIndex: Int,
    onSelectedTabIndex: (Int) -> Unit,
    userVM: UserViewModel,
    reproVM: ReproductorViewModel,
) {
    val pageState = rememberPagerState{ tabItems.size }

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
    ){index ->
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            SongList(songList = userVM.userSongList.getValue(tabItems[index]), reproVM = reproVM)
        }
    }
}




@Composable
fun SongList(
    songList: List<Song>,
    reproVM: ReproductorViewModel,
) {

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(items = songList) {
                ItemSongsCardList(reproVM, it)
            }
        }

    }

}

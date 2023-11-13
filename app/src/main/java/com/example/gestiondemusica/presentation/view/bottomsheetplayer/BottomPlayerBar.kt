package com.example.gestiondemusica.presentation.view.bottomsheetplayer

import androidx.compose.material3.BottomSheetScaffold
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.rememberBottomSheetScaffoldState
import androidx.compose.material3.rememberStandardBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomPlayerBar(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    content: @Composable () -> Unit,
) {

    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = rememberStandardBottomSheetState(skipHiddenState = false))

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomPlayerControls(musicVM, reproVM, scaffoldState)
        },
        sheetPeekHeight = if(!reproVM.song.song_Url.isNullOrBlank()){80.dp}else{0.dp},
        sheetSwipeEnabled = true,
    ) {

        content()

    }

}
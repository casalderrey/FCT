package com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import kotlinx.coroutines.launch

@Composable
fun SheetContentCollapse(
    screenHeightDp: Dp,
    fractionSheet: Float,
    minHeightSheet: Dp,
    onSheetClick: () -> Unit,
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
            .height((minHeightSheet * (1 - fractionSheet)))
            //.background(color = Color(255, 0, 0, (255 * (1 - fractionSheet)).toInt()))
            .graphicsLayer(alpha = 1f - fractionSheet)
            .clickable(onClick = onSheetClick),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        BottomProgressControl(reproVM)
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ImageRotateAnimation(
                bitmapImage = reproVM.currentSong?.image,
                isPlaying = reproVM.isPlayingState,
                circleSize = 64.dp,
            )
            CDItemDetails(
                titleSong = reproVM.currentSong?.titleSong?:"",
                titleArtist = reproVM.currentSong?.artistAlbum?:"",
                modifier = Modifier.weight(1f)
            )
            AudioControl(
                reproVM = reproVM,
            )
        }
    }
}


@Composable
fun BottomProgressControl(
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {
    var position by rememberSaveable { mutableStateOf(0F) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = reproVM.positionFlow) {
        // Recolectar en background
        scope.launch {
            reproVM.positionFlow.collect {
                position = it.toFloat()/(reproVM.currentSong?.duration?.toFloatOrNull()?:0.0f)
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LinearProgressIndicator(
            modifier = Modifier.fillMaxWidth(),
            progress = position,
            color = Color.Black,
            trackColor = Color.White,
        )
    }
}

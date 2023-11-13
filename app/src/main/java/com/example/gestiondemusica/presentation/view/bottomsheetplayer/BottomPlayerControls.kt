package com.example.gestiondemusica.presentation.view.bottomsheetplayer

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.BottomSheetScaffoldState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.R
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.utils.formatDuration
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomPlayerControls(
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    scaffoldState: BottomSheetScaffoldState,
){

    if (scaffoldState.bottomSheetState.isVisible) { /*TODO: Detectar cuando esta expandido*/
        Column(
            modifier = Modifier.fillMaxSize().padding(15.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                modifier = Modifier
                    .weight(1f)
                    .clip(RoundedCornerShape(15.dp)),
                contentScale = ContentScale.FillBounds,
                bitmap = if (reproVM.song.image != null) {
                    reproVM.song.image!!.asImageBitmap()
                } else {
                    ImageBitmap.imageResource(R.drawable.logo)
                },
                contentDescription = null
            )
            BottomRowPlayerControls(reproVM)
            BottomRowSliderControl(reproVM)
        }
    }else {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            BottomRowPlayerControls(reproVM)
            BottomRowSliderControl(reproVM)
        }
    }

}

@Composable
fun BottomRowPlayerControls(
    reproVM: ReproductorViewModel
) {
    var playPause by rememberSaveable { mutableStateOf(true) }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.KeyboardArrowLeft, contentDescription = null)
        }

        IconButton(
            onClick = {
                playPause = reproVM.isPlaying();
                if (reproVM.isPlaying()){ reproVM.pause() }else { reproVM.play() };
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {
            if (playPause){
                Icon(imageVector = Icons.Default.PlayArrow, contentDescription = null)
            }else{
                Icon(imageVector = Icons.Default.Close, contentDescription = null)
            }
        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.KeyboardArrowRight, contentDescription = null)
        }

    }

}

@Composable
fun BottomRowSliderControl(
    reproVM: ReproductorViewModel
) {
    var position by rememberSaveable { mutableStateOf(0L) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = reproVM.positionFlow) {
        // Recolectar en background
        scope.launch {
            reproVM.positionFlow.collect {
                position = it
            }
        }
    }

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Slider(
            value = position.toFloat(),
            onValueChange = { reproVM.setTimePosition(it.toLong()) },
            valueRange = 0f..(reproVM.song.duration.toFloatOrNull()?:0.0f),
            onValueChangeFinished = { },
            steps = 0,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

    Text(
        text = "${formatDuration(position)}/${formatDuration(reproVM.song.duration.toLongOrNull()?:0)}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 8.dp)
    )

}
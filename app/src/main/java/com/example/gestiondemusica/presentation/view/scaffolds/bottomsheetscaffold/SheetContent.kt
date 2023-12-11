package com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold

import android.graphics.drawable.Icon
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material.icons.twotone.KeyboardArrowLeft
import androidx.compose.material.icons.twotone.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.R
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel


@Composable
fun SheetContentConf(
    heightFraction: Float = 0.8f,
    content: @Composable ColumnScope.() -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction = heightFraction)
    ) {
        content()
    }
}

@Composable
fun SheetContentBoth(
    isCollapsed: Boolean,
    fractionSheet: Float,
    screenHeightDp: Dp,
    minHeightSheet: Dp,
    onSheetClick: () -> Unit,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier,
) {

    SheetContentExpanded(screenHeightDp, fractionSheet, minHeightSheet, onSheetClick, reproVM, userVM, modifier)
    SheetContentCollapse(screenHeightDp, fractionSheet, minHeightSheet, onSheetClick, reproVM, modifier)

}






@Composable
fun AudioControl(
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row {



            if(reproVM.songList.size > 1 && reproVM.getCurrentSongOfList() > 0) {
                IconButton(onClick = { reproVM.pause(); reproVM.setPreviousSong(); reproVM.play() }) {
                    Icon(painter = painterResource(R.drawable.ic_skip_previous), contentDescription = "")
                }
            }

            IconButton(onClick = {
                if (reproVM.isPlayingState) {
                    Log.d(
                        "DebugTag",
                        "Debug on: Compose.AudioControl,\n  voy a pausar: $ {reproVM.isPlaying() }"
                    )
                    reproVM.pause()
                } else {
                    Log.d(
                        "DebugTag",
                        "Debug on: Compose.AudioControl,\n  voy a play: ${ reproVM.isPlayingState }"
                    )
                    reproVM.play()
                };
            }) {
                Image(
                    painter = if (reproVM.isPlayingState) painterResource(R.drawable.ic_pause) else painterResource(R.drawable.ic_play),
                    contentDescription = null,
                    modifier = modifier
                        .clip(CircleShape),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                )
            }



            if(reproVM.songList.size > 1 && reproVM.getCurrentSongOfList() < reproVM.songList.size - 1){
                IconButton(onClick = { reproVM.pause(); reproVM.setNextSong(); reproVM.play() }) {
                    Icon(painter = painterResource(R.drawable.ic_skip_next), contentDescription = "")
                }
            }
        }

    }
}





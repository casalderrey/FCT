package com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.toggleable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Slider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong
import com.example.gestiondemusica.presentation.view.screens._common.listofLists
import com.example.gestiondemusica.utils.formatDuration
import kotlinx.coroutines.launch

@Composable
fun SheetContentExpanded(
    screenHeightDp: Dp,
    fractionSheet: Float,
    minHeightSheet: Dp,
    onSheetClick: () -> Unit,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height((screenHeightDp.value * fractionSheet).dp),
    ) {
        SheetContentExpandedHeader(reproVM, onSheetClick, modifier)
        SheetContentExpandedBody(reproVM, userVM, screenHeightDp, fractionSheet, onSheetClick, modifier)
    }
}


@Composable
fun SheetContentExpandedHeader(
    reproVM: ReproductorViewModel,
    onSheetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier
                .background(Color(125, 125, 125, 64))
                .fillMaxWidth()
                .clickable(onClick = onSheetClick),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
            )
        }
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                imageVector = Icons.Filled.Close,
                contentDescription = null,
                modifier = modifier
                    .clip(CircleShape)
                    .clickable(onClick = {
                        /* TODO: ¿Sobra algo? */
                        reproVM.setTimePosition(0)
                        reproVM.pause()
                        reproVM.setSongList(emptyList())
                        reproVM.setPlayerList()
                        onSheetClick()
                    })
            )
        }
    }
}


@Composable
fun SheetContentExpandedBody(
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    screenHeightDp: Dp,
    fractionSheet: Float,
    onSheetClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .height((screenHeightDp.value * fractionSheet).dp)
            .padding(10.dp)
            .graphicsLayer(alpha = 1f - (1 - fractionSheet)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        ImageSong(
            image = reproVM.currentSong?.image,
            modifier = modifier
                .size(250.dp)
                .clip(RoundedCornerShape(15.dp)),
            contentScale = ContentScale.Fit,
        )
        CDItemDetails(
            titleSong = reproVM.currentSong?.titleSong?:"",
            titleArtist = reproVM.currentSong?.artistAlbum?:"",
        )
        BottomRowSliderControl(
            reproVM = reproVM
        )
        AudioControl(
            reproVM = reproVM,
        )
        VariousControl(
            reproVM = reproVM,
            userVM = userVM,
        )

    }
}



@Composable
fun VariousControl(
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    var showDialog by remember { mutableStateOf(false) }
    Row(
        modifier = Modifier.padding(5.dp),
        horizontalArrangement = Arrangement.End,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // TODO: Revisar
        var isFavorite by mutableStateOf(userVM.isSongOnList(key = "Favorite", song = reproVM.currentSong))
        Row (modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray), horizontalArrangement = Arrangement.Center) {
        IconButton(
            onClick = {
                if (userVM.isSongOnList(key = "Favorite", song = reproVM.currentSong)) {
                    userVM.removeSongToList(key = "Favorite", song = reproVM.currentSong)
                    isFavorite = userVM.isSongOnList(key = "Favorite", song = reproVM.currentSong)
                }else {
                    userVM.addSongToList(key = "Favorite", song = reproVM.currentSong)
                    isFavorite = userVM.isSongOnList(key = "Favorite", song = reproVM.currentSong)
                }
            },
            modifier = Modifier.padding(bottom = 8.dp)
        ) {

                //TODO: cambiar a: userSongList
                if (isFavorite){
                    Icon(imageVector = Icons.Outlined.Favorite, contentDescription = "add to favorite")
                }else{
                    Icon(imageVector = Icons.TwoTone.Favorite, contentDescription = "added to favorite")
                }

            }
            Spacer(modifier = Modifier.width(10.dp))
            IconButton(onClick = { showDialog = true }) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Add to list")
            }

        }
    if(showDialog){
        listofLists(
            items = userVM.userSongList.keys.toList(),
            titleText = "Selecciona la lista",
            onConfirm = {
                if (userVM.isSongOnList(key = it[0], song = reproVM.currentSong)) {
                    userVM.removeSongToList(key = it[0], song = reproVM.currentSong)
                }else {
                    userVM.addSongToList(key = it[0], song = reproVM.currentSong)
                }
            },
            onShow = { showDialog =it})
    }
    }
}







@Composable
fun BottomRowSliderControl(
    reproVM: ReproductorViewModel,
    modifier: Modifier = Modifier,
) {
    var position by rememberSaveable { mutableStateOf(0L) }

    val scope = rememberCoroutineScope()

    LaunchedEffect(key1 = reproVM.positionFlow) {
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
            valueRange = 0f..(reproVM.currentSong?.duration?.toFloatOrNull()?:0.0f),
            onValueChangeFinished = { },
            steps = 0,
            modifier = Modifier.padding(horizontal = 16.dp)
        )
    }

    Text(
        text = "${formatDuration(position)}/${formatDuration(reproVM.currentSong?.duration?.toLongOrNull()?:0)}",
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier.padding(top = 8.dp)
    )

}

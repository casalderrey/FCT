package com.example.gestiondemusica.presentation.view.screens.lists

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.twotone.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.presentation.view.screens._common.Alert

@Composable
fun TabScaffold(
    musicVM:MusicViewModel,
    reproVM: ReproductorViewModel,
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    var tabItems = userVM.userSongList.map { it.key }
    var selectedTabIndex by rememberSaveable { mutableIntStateOf(0) }
    var showAlert by rememberSaveable { mutableStateOf(false)}


    if(tabItems.isNotEmpty()){
        Row() {
            IconButton(onClick = { showAlert = true }) {
                Icon(imageVector = Icons.TwoTone.Add, contentDescription = "Add to list")
            }
            ScrollableTabRow(selectedTabIndex = selectedTabIndex) {
                tabItems.forEachIndexed { index, item ->
                    Tab(
                        selected = true,
                        onClick = { selectedTabIndex = index },
                        text = { Text(text = item) },
                    )
                }
            }
        }
        TabPage(
            tabItems = tabItems,
            selectedTabIndex = selectedTabIndex,
            onSelectedTabIndex = { selectedTabIndex = it },
            reproVM = reproVM,
            userVM = userVM,
            musicVM = musicVM
        )
    }else {
        Row {
            IconButton(onClick = { showAlert = true }) {
                Icon(imageVector = Icons.TwoTone.Add, contentDescription = "Add to list")
            }
        }
        Text(text = "No hay listas.")
    }

    if(showAlert){
        Alert(onShow = {showAlert=it}, {userVM.createList(it)})
    }
}




package com.example.gestiondemusica.presentation.view.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SearchBarHeader(
    navController: NavController,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
    query: String,
    queryOnChange: (String) -> Unit,
    active: Boolean,
    activeOnChange: (Boolean) -> Unit,
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
    ) {
        SearchBar(
            modifier = Modifier.fillMaxWidth(),
            query = query,
            onQueryChange = { queryOnChange(it) },
            onSearch = { activeOnChange(false) },
            active = active,
            onActiveChange = { activeOnChange(it) },
            placeholder = { Text(text = "Buscar canción") },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Icon Search"
                )
            },
            trailingIcon = {
                if (active) {
                    Icon(
                        modifier = Modifier.clickable {
                            if (query.isNotEmpty()) {
                                queryOnChange("")
                            } else {
                                activeOnChange(false)
                            }
                        },
                        imageVector = Icons.Default.Close,
                        contentDescription = "Icon Cancel",
                    )
                }
            }
        ) {
            SearchBarContent(navController, musicVM, reproVM, query)
        }
    }

}
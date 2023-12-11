package com.example.gestiondemusica.presentation.view.scaffolds.scaffold

import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.gestiondemusica.R
import com.example.gestiondemusica.presentation.nav.AppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScaTopBar(navController: NavController, modifier: Modifier = Modifier) {
    Surface(tonalElevation = 1.5.dp, shadowElevation = 6.0.dp, ) {
        TopAppBar(
            modifier = Modifier.height(45.dp),
            title = {
                Row {
                    Image(
                        painter = painterResource(id = R.drawable.logo_inverso),
                        contentDescription = "logo",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Image(
                        modifier = Modifier.size(175.dp),
                        painter = painterResource(id = R.drawable.letra1),
                        contentDescription = "nombreApp",
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackground),
                    )
                }
            },
            actions = {
                Row(modifier = Modifier.fillMaxHeight(), verticalAlignment = Alignment.CenterVertically) {
                    IconButton(modifier = Modifier.size(24.dp), onClick = { navController.navigate(AppScreen.SearchScreen.route)}) {
                        Icon(Icons.Filled.Search, "back icon")
                    }
                }
            },
        )
    }
}
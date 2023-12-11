package com.example.gestiondemusica.presentation.view.screens.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.presentation.state.user.UserViewModel

@Composable
fun SelectTheme(
    userVM: UserViewModel,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(text = "Cambio de tema: ")
        Switch(checked = userVM.darkTheme, onCheckedChange = {userVM.setTheme(!userVM.darkTheme)})
    }
}

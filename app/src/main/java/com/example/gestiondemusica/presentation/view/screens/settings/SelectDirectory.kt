package com.example.gestiondemusica.presentation.view.screens.settings

import android.net.Uri
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.documentfile.provider.DocumentFile
import com.example.gestiondemusica.presentation.state.music.MusicViewModel

@Composable
fun SelectDirectory(
    musicVM: MusicViewModel,
    modifier: Modifier = Modifier,
) {
    var context = LocalContext.current
    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.OpenDocumentTree() ,
        onResult = { uri ->
            var ruta = DocumentFile.fromTreeUri(context, uri?: Uri.EMPTY)
            ruta?.let(musicVM::getMusicListFromUri)
        },
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(text = "Cambiar ruta ")
        ContentSelector(selectVideoLauncher)
    }

}

@Composable
private fun ContentSelector(selectVideoLauncher: ManagedActivityResultLauncher<Uri?, Uri?>) {
    IconButton(onClick = {
        selectVideoLauncher.launch(null)
    }) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = "Select directory"
        )
    }
}
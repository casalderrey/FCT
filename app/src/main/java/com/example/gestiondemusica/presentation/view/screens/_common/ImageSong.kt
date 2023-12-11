package com.example.gestiondemusica.presentation.view.screens._common

import android.graphics.Bitmap
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.gestiondemusica.R

@Composable
fun ImageSong(
    image: Bitmap?,
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Fit,
) {
    if(image != null) {
        Image(
            bitmap = image.asImageBitmap(),
            contentDescription = "image song",
            contentScale = contentScale,
            modifier = modifier
        )
    }else {
        Image(
            painter = painterResource(R.drawable.ic_music_note),
            contentDescription = "image default song",
            contentScale = ContentScale.Fit
        )
    }
}

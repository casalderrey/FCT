package com.example.gestiondemusica.presentation.view.scaffolds.bottomsheetscaffold

import android.graphics.Bitmap
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.gestiondemusica.presentation.view.screens._common.ImageSong
import com.example.gestiondemusica.ui.theme.DarkBackground
import com.example.gestiondemusica.ui.theme.DarkBackgroundOpacity

@Composable
fun ImageRotateAnimation(
    bitmapImage: Bitmap?,
    isPlaying: Boolean,
    circleSize: Dp,
    modifier: Modifier = Modifier,
) {
    var currentRotation by remember { mutableStateOf(0f) }

    val rotation = remember { Animatable(currentRotation) }


    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            // Infinite repeatable rotation when is playing
            rotation.animateTo(
                targetValue = currentRotation + 360f,
                animationSpec = infiniteRepeatable(
                    animation = tween(3000, easing = LinearEasing),
                    repeatMode = RepeatMode.Restart
                )
            ) {
                currentRotation = value
            }
        } else {
            if (currentRotation > 0f) {
                // Slow down rotation on pause
                rotation.animateTo(
                    targetValue = currentRotation + 50,
                    animationSpec = tween(
                        durationMillis = 1250,
                        easing = LinearOutSlowInEasing
                    )
                ) {
                    currentRotation = value
                }
            }
        }
    }

    CDItemImage(bitmapImage = bitmapImage, rotationDegrees = rotation.value, circleSize = circleSize)
}

@Composable
fun CDItemImageDetails(
    bitmapImage: Bitmap?,
    titleSong: String,
    titleArtist: String,
    rotationDegrees: Float,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = Modifier.padding(8.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        CDItemImage(
            bitmapImage = bitmapImage,
            rotationDegrees = rotationDegrees,
        )
        CDItemDetails(
            titleSong = titleSong,
            titleArtist = titleArtist,
        )
    }
}

@Composable
fun CDItemImage(
    bitmapImage: Bitmap?,
    rotationDegrees: Float,
    circleSize: Dp = 128.dp,
    modifier: Modifier = Modifier,
) {
    Box(modifier = Modifier.size(circleSize).padding(5.dp), contentAlignment = Alignment.CenterStart) {

        ImageSong(
            image = bitmapImage,
            modifier = Modifier
                .clip(CircleShape)
                .rotate(rotationDegrees)
                .align(Alignment.Center),
            contentScale = ContentScale.Fit,
        )

        Canvas(
            modifier = Modifier
                .size(circleSize / 4)
                .align(Alignment.Center)
        ) {
            drawCircle(
                color = DarkBackgroundOpacity,
                radius = circleSize.value/(8/3f),
                style = Fill
            )
            drawCircle(
                color = DarkBackground,
                radius = circleSize.value/4,
                style = Fill
            )
        }
    }
}

@Composable
fun CDItemDetails(
    titleSong: String,
    titleArtist: String,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.Start,
    ) {
        Text(
            text = titleSong,
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
        Text(
            text = titleArtist,
            color = Color.Gray,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )
    }
}

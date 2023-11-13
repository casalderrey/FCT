package com.example.gestiondemusica.data.model

import android.graphics.Bitmap

data class SongEntity(
    val cdTrackNumber: String = "",
    val album: String = "",
    val artist: String = "",
    val author: String = "",
    val composer: String = "",
    val date: String = "",
    val genre: String = "",
    val title: String = "",
    val year: String = "",
    val duration: String = "",
    val numTracks: String = "",
    val writer: String = "",
    val mimetype: String = "",
    val albumArtist: String = "",
    val discNumber: String = "",
    val compilation: String = "",
    val hasAudio: String = "",
    val bitrate: String = "",
    val samplerate: String = "",
    val songPath: String = "",
    val image: Bitmap? = null,
)

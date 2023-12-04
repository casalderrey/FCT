package com.example.gestiondemusica.domain.model

import android.graphics.Bitmap
import com.example.gestiondemusica.data.model.SongEntity
import com.example.gestiondemusica.utils.extension.getNumCdTrackNumber
import com.example.gestiondemusica.utils.extension.getNumDisc
import com.example.gestiondemusica.utils.extension.getTotalNumDisc

data class Song(
    val titleSong:   String? = null,
    val artistSong:  String? = null,
    val titleAlbum:  String? = null,
    val artistAlbum: String? = null,
    val author:      String? = null,
    val composer:    String? = null,
    val writer:      String? = null,
    val group:       String? = null,
    val genre:       String? = null,
    val date:        String? = null,
    val year:        String? = null,
    val track:       String? = null,
    val trackOf:     String? = null,
    val disc:        String? = null,
    val discOf:      String? = null,
    val compilation: String? = null,
    val bmp:         String? = null,
    val comments:    String? = null,
    val lyric:       String? = null,
    val image:       Bitmap? = null,
    val duration:    String? = null,
    val mimetype:    String? = null,
    val hasAudio:    String? = null,
    val bitrate:     String? = null,
    val samplerate:  String? = null,
    val songUrl:     String? = null,
    val countListen: Int = 0,
)

fun SongEntity.toDomain()= Song(
    titleSong = title.ifBlank { null },
    artistSong = artist.ifBlank { null },
    titleAlbum = album.ifBlank { null },
    artistAlbum = albumArtist.ifBlank { null },
    author = author.ifBlank { null },
    composer = composer.ifBlank { null },
    writer = writer.ifBlank { null },
    group = "".ifBlank { null },
    genre = genre.ifBlank { null },
    date = date.ifBlank { null },
    year = year.ifBlank { null },
    track = cdTrackNumber.getNumCdTrackNumber.ifBlank { null },
    trackOf = null, //cdTrackNumber.getTotalCdTrackNumber.ifBlank { null },
    disc = discNumber.getNumDisc.ifBlank { null },
    discOf = discNumber.getTotalNumDisc.ifBlank { null },
    compilation = compilation.ifBlank { null },
    bmp = "".ifBlank { null },
    comments = "".ifBlank { null },
    lyric = "".ifBlank { null },
    image = image,
    duration = duration.ifBlank { null },
    mimetype = mimetype.ifBlank { null },
    hasAudio = hasAudio.ifBlank { null },
    bitrate = bitrate.ifBlank { null },
    samplerate = samplerate.ifBlank { null },
    songUrl = songPath.ifBlank { null },
)


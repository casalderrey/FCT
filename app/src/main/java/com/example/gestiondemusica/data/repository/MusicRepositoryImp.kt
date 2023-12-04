package com.example.gestiondemusica.data.repository

import android.content.Context
import android.graphics.BitmapFactory
import android.media.MediaMetadataRetriever
import android.util.Log
import androidx.documentfile.provider.DocumentFile
import com.example.gestiondemusica.data.model.SongEntity
import com.example.gestiondemusica.presentation.state.Results
import java.io.File

class MusicRepositoryImp(
    private val mmr:MediaMetadataRetriever,
    private val context: Context
): MusicRepository {

    override suspend fun getSongFromUri(uri: DocumentFile?): Results<List<SongEntity>> {
        Log.d("DebugTag", "Debug on: MusicRepositoryImp.getSongFromUri,\n  uri value: ${uri?.uri}")
        try {
            val listSong = mutableListOf<SongEntity>()

            uri?.listFiles()
                ?.filter {
                    if(it.name!=null) {
                        it.canRead() && it.isFile && it.name!!.endsWith(".mp3")
                    }else {
                        false
                    }
                }?.map {
                    mmr.setDataSource( context, it.uri)
                    var song: SongEntity = SongEntity(
                        cdTrackNumber = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER) ?: "",
                        album         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)           ?: "",
                        artist        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)          ?: "",
                        author        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR)          ?: "",
                        composer      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER)        ?: "",
                        date          = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)            ?: "",
                        genre         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)           ?: "",
                        title         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)           ?: "",
                        year          = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)            ?: "",
                        duration      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)        ?: "",
                        numTracks     = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS)      ?: "",
                        writer        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_WRITER)          ?: "",
                        mimetype      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)        ?: "",
                        albumArtist   = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST)     ?: "",
                        discNumber    = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER)     ?: "",
                        compilation   = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPILATION)     ?: "",
                        hasAudio      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_AUDIO)       ?: "",
                        bitrate       = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE)         ?: "",
                        samplerate    = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_SAMPLERATE)      ?: "",
                        image         = if (mmr.embeddedPicture != null) {
                                            BitmapFactory.decodeByteArray(mmr.embeddedPicture, 0,mmr.embeddedPicture!!.size)
                                        } else {
                                            null
                                        },
                        songPath      = it.uri.path?.split(":")?.getOrNull(1) ?: ""
                    )

                    if (listSong.none { song.songPath == it.songPath }) {
                        listSong.add(song)
                    }

                }

            Log.i("InfoTag", "Info on: MusicRepositoryImp.getSongFromUri,\n  Read song: ${listSong}")
            return Results.success(listSong)

        }catch (ex: Exception){
            Log.e("ErrorTag", "Error on: MusicRepositoryImp.getSongFromUri,\n  Exception: ${ex.message}")
            return Results.error(ex)
        }

    }

    override suspend fun getSongFromFile(file: File): Results<List<SongEntity>> {
        Log.d("DebugTag", "Debug on: MusicRepositoryImp.getSongFromFile,\n  file name value: ${file.name}")
        try {
            val listSong = mutableListOf<SongEntity>()
            file.listFiles()
                ?.filter {
                    it.canRead() && it.isFile && it.name.endsWith(".mp3")
                }?.map {
                    mmr.setDataSource(it.path)
                    var song: SongEntity = SongEntity(
                        cdTrackNumber = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_CD_TRACK_NUMBER) ?: "",
                        album         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM)           ?: "",
                        artist        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)          ?: "",
                        author        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_AUTHOR)          ?: "",
                        composer      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPOSER)        ?: "",
                        date          = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DATE)            ?: "",
                        genre         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_GENRE)           ?: "",
                        title         = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)           ?: "",
                        year          = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_YEAR)            ?: "",
                        duration      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)        ?: "",
                        numTracks     = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_NUM_TRACKS)      ?: "",
                        writer        = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_WRITER)          ?: "",
                        mimetype      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_MIMETYPE)        ?: "",
                        albumArtist   = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUMARTIST)     ?: "",
                        discNumber    = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER)     ?: "",
                        compilation   = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_COMPILATION)     ?: "",
                        hasAudio      = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_HAS_AUDIO)       ?: "",
                        bitrate       = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_BITRATE)         ?: "",
                        samplerate    = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_SAMPLERATE)      ?: "",
                        image         = if (mmr.embeddedPicture != null) {
                            BitmapFactory.decodeByteArray(mmr.embeddedPicture, 0,mmr.embeddedPicture!!.size)
                        } else {
                            null
                        },
                        songPath      = it.path,
                    )

                    Log.d("DebugTag", "Debug on: MusicRepository.getSong,\n  Object creado: ${song}")

                    if (listSong.none { song.songPath == it.songPath }) {
                        listSong.add(song)
                    }

                    Log.d("DebugTag", "Debug on: MusicRepository.getSongFromFile,\n  Object listado")
                }

            Log.i("InfoTag", "Info on: MusicRepositoryImp.getSongFromFile,\n  Read song: ${listSong}")
            return Results.success(listSong)

        }catch (ex: Exception){
            Log.e("ErrorTag", "Error on: MusicRepositoryImp.getSongFromFile,\n  Exception: ${ex.message}")
            return Results.error(ex)
        }
    }

}

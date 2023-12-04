package com.example.gestiondemusica.data.repository

import androidx.documentfile.provider.DocumentFile
import com.example.gestiondemusica.data.model.SongEntity
import com.example.gestiondemusica.presentation.state.Results
import java.io.File

interface MusicRepository {
    suspend fun getSongFromUri(uri: DocumentFile?): Results<List<SongEntity>>
    suspend fun getSongFromFile(file: File): Results<List<SongEntity>>
}
package com.example.gestiondemusica.data.repository

import com.example.gestiondemusica.data.model.SongEntity
import com.example.gestiondemusica.presentation.state.Results
import java.io.File

interface MusicRepository {
    /*TODOCambiar valor por defecto(poner Documents/music), lo seleccionara el usuario*/
    suspend fun getSong(file: File): Results<List<SongEntity>>
}
package com.example.gestiondemusica.presentation.state.user

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gestiondemusica.domain.model.Song

class UserViewModel(): ViewModel() {

    private var _darkTheme by mutableStateOf<Boolean>(false)
    val darkTheme get() = _darkTheme

    private var _userSongList = mutableStateMapOf<String, MutableList<Song>>()
    val userSongList get() = _userSongList.toMap()

    fun setTheme(boolean: Boolean) {
        Log.d("DebugTag", "Debug on: UserViewModel.setTheme,\n  Cambio el tema: $boolean")
        _darkTheme = boolean
    }

    fun addSongToList(key: String = "Favorite", song: Song) {
        Log.d("DebugTag", "Debug on: UserViewModel.addSongToList,\n  - Añadida la cancion: $song  - A la lista: $key")
        _userSongList.getOrPut(key) {
            mutableListOf()
        }.add(song)
    }

    fun removeSongToList(key: String = "Favorite", song: Song) {
        val songList = _userSongList[key] ?: return
        if(songList.contains(song)) {
            songList.remove(song)
            _userSongList[key] = songList
            Log.d("DebugTag", "Debug on: UserViewModel.removeSongToList,\n  - Eliminada cancion de la lista: $song")
        }
    }

    fun isSongOnList(key: String = "Favorite", song: Song):Boolean {
        Log.d("DebugTag", "Debug on: UserViewModel.isSongOnList,\n  - Busco la cancion: $song  - En la lista: $key")
        val songList = _userSongList[key] ?: return false
        return songList.contains(song)
    }

}

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
        Log.i("InfoTag", "Info on: UserViewModel.setTheme,\n  - Change Theme: $boolean")
        _darkTheme = boolean
    }

    fun createList(key: String):Boolean{
        if(_userSongList.keys.contains(key)){
            return false
        }
        _userSongList.getOrPut(key) {
            mutableListOf()
        }

        return true;
    }

    fun addSongToList(key: String, song: Song?) {
        if(song != null && !isSongOnList(key, song)) {
            Log.i("InfoTag", "Info on: UserViewModel.addSongToList,\n  - Add song: ${song.titleSong} - To list: $key")
            _userSongList.getOrPut(key) {
                mutableListOf()
            }!!.add(song)
        }
    }

    fun removeSongToList(key: String, song: Song?) {
        if(song != null) {
            val songList = _userSongList[key] ?: return
            if(songList.contains(song)) {
                songList.remove(song)
                _userSongList[key] = songList
                Log.d("DebugTag", "Debug on: UserViewModel.removeSongToList,\n  - Remove Song ${song.titleSong} - To list: $key")
            }
        }
    }

    fun isSongOnList(key: String, song: Song?): Boolean {
        if(song != null) {
            Log.d("DebugTag", "Debug on: UserViewModel.isSongOnList,\n  - Busco la cancion: $song  - En la lista: $key")
            val songList = _userSongList[key] ?: return false
            return songList.contains(song)
        }
        return false
    }


}

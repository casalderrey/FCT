package com.example.gestiondemusica.presentation.state.musicList

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondemusica.data.repository.MusicRepositoryImp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.domain.model.toDomain
import com.example.gestiondemusica.presentation.state.Results
import com.example.gestiondemusica.utils.FilesUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class MusicViewModel(
    private val musicRepository: MusicRepositoryImp,
): ViewModel() {

    private var _listSongs:List<Song> = mutableListOf<Song>(Song())  /*TODO: Revisar*/
    val listSongs: List<Song> get() = _listSongs.toList()

    fun loadListSong(dir: String = "${FilesUtils.RUTA_DISPOSITIVO}/bluetooth") {
        viewModelScope.launch(Dispatchers.IO) {

            var result = musicRepository.getSong(File(dir))

            when(result){
                is Results.success -> {
                    _listSongs = result.data.map { it.toDomain() }
                    Log.i("InfoTag", "Info on: MusicViewModel.loadListSong,\n  _listSongs: ${_listSongs}")
                }
                is Results.error -> {
                    _listSongs = emptyList()
                    Log.e("ErrorTag", "Error on: MusicViewModel.loadListSong,\n  Message: ${result.exception.message}")
                }
            }

        }
    }

}
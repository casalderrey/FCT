package com.example.gestiondemusica.presentation.state.music

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.documentfile.provider.DocumentFile
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gestiondemusica.data.repository.MusicRepositoryImp
import com.example.gestiondemusica.domain.model.Song
import com.example.gestiondemusica.domain.model.toDomain
import com.example.gestiondemusica.presentation.state.Results
import com.example.gestiondemusica.presentation.state.UIState
import com.example.gestiondemusica.utils.FilesUtils
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.internal.filterList
import java.io.File

class MusicViewModel(
    private val musicRepository: MusicRepositoryImp,
    private val context: Context,
): ViewModel() {

    private var _uiState = MutableStateFlow<UIState>(UIState.NOTHING)
    val uiState: StateFlow<UIState> get() = _uiState.asStateFlow()

    private var _listSongs:List<Song?> = mutableListOf<Song?>(null)  /*TODO: Revisar*/
    val listSongs: List<Song?> get() = _listSongs.toList()


    private var _searchList: List<Song> = mutableListOf<Song>()  /*TODO: Revisar*/
    val searchList: List<Song> get() = _searchList.toList()



    private var _dirSongs by mutableStateOf(FilesUtils.getDirectory(context, "/Download"))
    val dirSongs:DocumentFile? get() = _dirSongs

    private var _userList:Map<String?, List<Song?>> = mutableMapOf()
    val userList:Map<String?, List<Song?>> get() = _userList

    private var _userSongList= mutableListOf<Song?>(null)
    val userSongList: List<Song?> get() = _userSongList.toList()


    fun getMusicListFromUri(uri: DocumentFile? = dirSongs) {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UIState.LOADING
            var result = musicRepository.getSongFromUri(uri)

            when(result){
                is Results.success -> {
                    _listSongs = result.data.map { it.toDomain() }
                    _uiState.value = UIState.SUCCESSFUL
                    Log.i("InfoTag", "Info on: MusicViewModel.getMusicListFromUri,\n  _listSongs: ${_listSongs}")
                }
                is Results.error -> {
                    _listSongs = emptyList()
                    _uiState.value = UIState.ERROR
                    Log.e("ErrorTag", "Error on: MusicViewModel.getMusicListFromUri,\n  Message: ${result.exception.message}")
                }
            }

        }
    }

    fun getMusicListFromStringDirectory(dir: String = "${FilesUtils.RUTA_DISPOSITIVO}/bluetooth") {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.value = UIState.LOADING
            var result = musicRepository.getSongFromFile(File(dir))

            when(result){
                is Results.success -> {
                    _listSongs = result.data.map { it.toDomain() }
                    _uiState.value = UIState.SUCCESSFUL
                    Log.i("InfoTag", "Info on: MusicViewModel.getMusicListFromStringDirectory,\n  _listSongs: ${_listSongs}")
                }
                is Results.error -> {
                    _listSongs = emptyList()
                    _uiState.value = UIState.ERROR
                    Log.e("ErrorTag", "Error on: MusicViewModel.getMusicListFromStringDirectory,\n  Message: ${result.exception.message}")
                }
            }

        }
    }
    fun getAllArtist(): Map<String?, List<Song?>> = _listSongs.groupBy {
        it?.artistSong
    }
    fun getAllGenres(): Map<String?, List<Song?>> = _listSongs.groupBy {
        it?.genre
    }

    fun findSong(query: String) {
        _searchList = _listSongs.filterList {
            if(!this?.titleSong.isNullOrBlank()) {
                this?.titleSong!!.contains(query, true)
            }else {
                false
            }
        }.filterNotNull()
    }
  /*  fun getAllMisListas(nombreLista:String, song: Song): Map<String?, List<Song?>> {
        if(!_userList.keys.contains(nombreLista)){

            _userList = mapOf(nombreLista to emptyList<Song>())

        }


        _userSongList.add(song)

    }
*/
}

package com.example.gestiondemusica.presentation.state.palyer

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.setValue
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.example.gestiondemusica.data.repository.ReproductorRepository
import com.example.gestiondemusica.domain.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow

class ReproductorViewModel(
    private val reproductorRepository: ReproductorRepository,
): ViewModel() {

    private var _currentSong: Song? by mutableStateOf(null)
    val currentSong: Song? get() = _currentSong

    private var _songList = mutableStateListOf<Song>()
    val songList: List<Song> get() = _songList.toList()

    private var _isPlayingState by mutableStateOf(reproductorRepository.isPlaying())
    val isPlayingState:Boolean get() = _isPlayingState

    val positionFlow = flow<Long> {
        while (true) {
            if(currentSong != null && currentSong?.duration != null) {
                if(currentSong!!.duration!!.isDigitsOnly() && currentSong!!.duration!!.isNotEmpty()) {
                    if(getCurrentPosition() >= currentSong!!.duration!!.toLong()) {               // TODO: Mejorar, no es preciso?, hay que hacer un mayor
                        pause()
                        setTimePosition(0)
                    }
                }
            }
            emit(getCurrentPosition())
            delay(100)
        }
    }

    fun reset(){
        _songList.clear()
    }

    fun setSongList(listSongs: List<Song>) {
        _songList.clear()
        _songList.addAll(listSongs)
    }
    fun setSongList(song: Song) {
        _songList.clear()
        _songList.add(song)
    }

    fun setPlayerList() {
        if(songList.isEmpty()) {
            _currentSong = null
            reproductorRepository.setPlayerList(emptyList())
        }else {
            _currentSong = songList[reproductorRepository.getCurrentSongOfList()]
            reproductorRepository.setPlayerList(songList.map { it.songUrl ?: "" })
        }

    }

    fun play() {
        reproductorRepository.play()
        _isPlayingState = reproductorRepository.isPlaying()
    }

    fun pause() {
        reproductorRepository.pause()
        _isPlayingState = reproductorRepository.isPlaying()
    }

    fun setTimePosition(position:Long){
        reproductorRepository.setTime(position)
    }

    fun getCurrentPosition(): Long {
        return reproductorRepository.getCurrentPosition()
    }

    fun getCurrentSongOfList(): Int {
        return reproductorRepository.getCurrentSongOfList()
    }

    fun setPreviousSong() {
        _currentSong = songList[reproductorRepository.setPreviousSong()]
        reproductorRepository.seekToPreviousMediaItem()
        _isPlayingState = reproductorRepository.isPlaying()
    }

    fun setNextSong() {
        _currentSong = songList[reproductorRepository.setNextSong()]
        reproductorRepository.seekToNextMediaItem()
        _isPlayingState = reproductorRepository.isPlaying()
    }

}

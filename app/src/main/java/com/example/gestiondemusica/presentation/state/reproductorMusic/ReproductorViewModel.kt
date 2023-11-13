package com.example.gestiondemusica.presentation.state.reproductorMusic

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.gestiondemusica.data.repository.ReproductorRepository
import com.example.gestiondemusica.domain.model.Song
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow

class ReproductorViewModel(
    private val reproductorRepository: ReproductorRepository,
): ViewModel() {

    private var _song by mutableStateOf(Song())
    val song:Song get() = _song

    private var _isPlayingState = MutableStateFlow(reproductorRepository.isPlaying())
    val isPlayingState: StateFlow<Boolean> get() = _isPlayingState

    private var _currentPosition = MutableStateFlow(reproductorRepository.getCurrentPosition())
    val currentPosition: StateFlow<Long> = _currentPosition

    val positionFlow = flow<Long> {
        //_isPlaying = isPlaying()
        while (true) {
            emit(getCurrentPosition())
            //Log.d("DebugTag", "Debug on: ReproductorViewModel.Compose,\n  fuc: ${isPlaying()}  var : $_isPlayingState")
            delay(100)
        }
    }

    fun updateIsPlaying() {
        _isPlayingState.value = reproductorRepository.isPlaying()
    }

    fun setSong(song: Song) {
        _song = song.copy(
            title_song = song.title_song,
            artist_song = song.artist_song,
            title_album = song.title_album,
            artist_album = song.artist_album,
            author = song.author,
            composer = song.composer,
            writer = song.writer,
            group = song.group,
            genre = song.genre,
            date = song.date,
            year = song.year,
            track = song.track,
            track_of = song.track_of,
            disc = song.disc,
            disc_of = song.disc_of,
            compilation = song.compilation,
            bmp = song.bmp,
            comments = song.comments,
            lyric = song.lyric,
            image = song.image,
            duration = song.duration,
            mimetype = song.mimetype,
            hasAudio = song.hasAudio,
            bitrate = song.bitrate,
            samplerate = song.samplerate,
            song_Url = song.song_Url
        )
    }


    fun setPlayer() {
        reproductorRepository.setPlayer(song.song_Url)
    }

    fun play() {
        reproductorRepository.play()
    }

    fun pause() {
        reproductorRepository.pause()
    }

    fun getContentDuration() = reproductorRepository.getContentDuration()

    fun getCurrentPosition() = reproductorRepository.getCurrentPosition()

    fun getDuration() = reproductorRepository.getDuration()

    fun isPlaying() = reproductorRepository.isPlaying()

    fun setTimePosition(position:Long){
        reproductorRepository.setTime(position)
    }
}
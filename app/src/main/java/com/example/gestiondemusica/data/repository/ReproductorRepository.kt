package com.example.gestiondemusica.data.repository

import androidx.media3.common.MediaItem
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer

class ReproductorRepository(
    private val exoPlayer: ExoPlayer,
) {

    fun setPlayer(urlSong: String) {
        val mediaItem = MediaItem.fromUri(urlSong)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

    fun setPlayerList(urlSongs: List<String>) {
        val mediaItems:List<MediaItem> = urlSongs.map(){ MediaItem.fromUri(it)}
        exoPlayer.setMediaItems(mediaItems)
        exoPlayer.prepare()
    }

    fun seekToNextMediaItem() {
        exoPlayer.seekToNextMediaItem()
    }

    fun seekToPreviousMediaItem() {
        exoPlayer.seekToPreviousMediaItem()
    }

    fun seekToNext() {
        exoPlayer.seekToNext()
    }

    fun seekToPrevious() {
        exoPlayer.seekToPrevious()
    }

    fun getCurrentSongOfList():Int = exoPlayer.currentMediaItemIndex

    fun setNextSong():Int = exoPlayer.nextMediaItemIndex

    fun setPreviousSong():Int = exoPlayer.previousMediaItemIndex



    fun play() {
        exoPlayer.play()
    }

    fun pause() {
        exoPlayer.pause()
    }

    fun isPlaying() = exoPlayer.isPlaying

    fun getContentDuration() = exoPlayer.contentDuration

    fun getCurrentPosition() = exoPlayer.currentPosition

    fun getDuration() = exoPlayer.duration

    fun setTime(position:Long) {
        exoPlayer.seekTo(position)
    }

    fun getD() = exoPlayer.contentPosition

}
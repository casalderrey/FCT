package com.example.gestiondemusica.data.repository

import androidx.media3.common.MediaItem
import androidx.media3.exoplayer.ExoPlayer

class ReproductorRepository(
    private val exoPlayer: ExoPlayer,
) {

    fun setPlayer(urlSong: String) {
        val mediaItem = MediaItem.fromUri(urlSong)
        exoPlayer.setMediaItem(mediaItem)
        exoPlayer.prepare()
    }

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

    fun getD() = exoPlayer.contentPosition
    fun setTime(position:Long) {
        exoPlayer.seekTo(position)

    }


//    fun player(urlSong:String){
//        val mediaItem = MediaItem.fromUri(urlSong)
//        val mediaSourceFactory = DefaultMediaSourceFactory(context)
//        val mediaSource = mediaSourceFactory.createMediaSource(mediaItem)
//
//        exoPlayer.setMediaSource(mediaSource)
//        exoPlayer.prepare()
//    }


}
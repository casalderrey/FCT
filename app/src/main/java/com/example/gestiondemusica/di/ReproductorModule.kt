package com.example.gestiondemusica.di

import android.media.MediaMetadataRetriever
import androidx.media3.exoplayer.ExoPlayer
import com.example.gestiondemusica.data.repository.MusicRepositoryImp
import com.example.gestiondemusica.data.repository.ReproductorRepository
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val reproductorModule = module {
    //viewModel { MusicViewModel(androidContext()) }
    //single { androidContext() }
    single { ExoPlayer.Builder(get()).build() }
    single { MediaMetadataRetriever() }
    single<MusicRepositoryImp> { MusicRepositoryImp(get()) }
    single<ReproductorRepository> { ReproductorRepository(get()) }

    viewModel { MusicViewModel(get()) }
    viewModel { ReproductorViewModel(get()) }
    viewModel { UserViewModel() }
}


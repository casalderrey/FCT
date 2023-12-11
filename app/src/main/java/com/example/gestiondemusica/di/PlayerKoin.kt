package com.example.gestiondemusica.di

import android.media.MediaMetadataRetriever
import androidx.media3.exoplayer.ExoPlayer
import com.example.gestiondemusica.data.repository.MusicRepositoryImp
import com.example.gestiondemusica.data.repository.ReproductorRepository
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val playerModule = module {
    single { ExoPlayer.Builder(get()).build() }
    single { MediaMetadataRetriever() }
    single<MusicRepositoryImp> { MusicRepositoryImp(get(), get()) }
    single<ReproductorRepository> { ReproductorRepository(get()) }

    viewModel { MusicViewModel(get(), androidContext()) }
    viewModel { ReproductorViewModel(get()) }
    viewModel { UserViewModel() }
}
package com.example.gestiondemusica

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.gestiondemusica.presentation.nav.AppNavigation
import com.example.gestiondemusica.presentation.state.reproductorMusic.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.musicList.MusicViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.ui.theme.GestiondeMusicaTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val userVM = viewModel<UserViewModel>()
            GestiondeMusicaTheme (darkTheme = userVM.darkTheme){
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                    var musicVM = getViewModel<MusicViewModel>()
                    var reproVM = getViewModel<ReproductorViewModel>()

                    musicVM.loadListSong();

                    AppNavigation(musicVM = musicVM, reproVM = reproVM, userVM = userVM)

                }
            }
        }

    }
}
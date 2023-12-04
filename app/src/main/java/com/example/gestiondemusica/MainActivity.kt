package com.example.gestiondemusica

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.gestiondemusica.presentation.nav.AppNavigation
import com.example.gestiondemusica.presentation.state.music.MusicViewModel
import com.example.gestiondemusica.presentation.state.palyer.ReproductorViewModel
import com.example.gestiondemusica.presentation.state.user.UserViewModel
import com.example.gestiondemusica.ui.theme.GestorMusicaTheme
import org.koin.androidx.compose.getViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        setContent {
            var musicVM = getViewModel<MusicViewModel>()
            var reproVM = getViewModel<ReproductorViewModel>()
            val userVM = getViewModel<UserViewModel>()

            Splash(splashScreen = splashScreen, musicVM = musicVM)
            ActivityMain(userVM, musicVM, reproVM)

        }
    }
}

@Composable
fun ActivityMain(
    userVM: UserViewModel,
    musicVM: MusicViewModel,
    reproVM: ReproductorViewModel,
) {
    GestorMusicaTheme(darkTheme = userVM.darkTheme) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {

            musicVM.listSongs.map {
                Log.d("DebugTag", "Debug on: MainActivity.onCreate,\n  $it")
                if (it != null) {
                    userVM.addSongToList(key = "Todas", song = it)
                }
            }
            AppNavigation(musicVM = musicVM, reproVM = reproVM, userVM = userVM)

        }
    }
}

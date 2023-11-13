package com.example.gestiondemusica

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity: ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }
        super.onCreate(savedInstanceState)
        lifecycleScope.launchWhenCreated {
            delay(3000)

            val intent = Intent(this@SplashActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}



//class SplashActivity: ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        val screenSplash = installSplashScreen()
//        super.onCreate(savedInstanceState)
//        setContent {
//            screenSplash.setKeepOnScreenCondition{true}
//        //}
//        //
//        //lifecycleScope.launchWhenCreated {
//        //    delay(3000)
//
//            val intent = Intent(this@SplashActivity, MainActivity::class.java)
//            startActivity(intent)
//            finish()
//        }
//    }
//
//}



/*
class SplashActivity: ComponentActivity() {
    val screenSplash = installSplashScreen()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}
*/
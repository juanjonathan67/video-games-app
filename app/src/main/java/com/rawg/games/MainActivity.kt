package com.rawg.games

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.CompositionLocalProvider
import androidx.navigation.compose.rememberNavController
import com.rawg.games.di.DaggerViewModelFactory
import com.rawg.games.ui.screens.VideoGameApp
import com.rawg.games.ui.theme.VideoGamesAppTheme
import com.rawg.games.utils.LocalViewModelFactory
import dagger.android.AndroidInjection
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var daggerViewModelFactory: DaggerViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompositionLocalProvider(
                LocalViewModelFactory provides daggerViewModelFactory
            ) {
                VideoGamesAppTheme {
                    VideoGameApp(rememberNavController())
                }
            }
        }
    }
}
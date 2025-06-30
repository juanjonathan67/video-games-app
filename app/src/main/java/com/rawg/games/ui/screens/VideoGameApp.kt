package com.rawg.games.ui.screens

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.rawg.games.ui.components.bottomBar.BottomBar
import com.rawg.games.ui.navigation.AppNavHost

@Composable
internal fun VideoGameApp(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    Scaffold(
        bottomBar = { BottomBar(navHostController) }
    ) { paddingValues: PaddingValues ->
        AppNavHost(
            navHostController = navHostController,
            modifier = modifier.padding(paddingValues)
        )
    }
}
package com.rawg.games.ui.screens.home

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable

@Composable
internal fun HomeScreen() {
    HomeScreenContent("Testing")
}

@Composable
internal fun HomeScreenContent(
    name: String
) {
    Text(name)
}
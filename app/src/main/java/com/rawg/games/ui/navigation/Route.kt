package com.rawg.games.ui.navigation

sealed class Route(val route: String) {
    data object Home: Route("home")
    data object Favorite: Route("Favorite")
    data object Settings: Route("settings")
}
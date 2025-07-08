package com.rawg.games.ui.navigation

sealed class Route(val route: String) {
    data object Home: Route("home")
    data object Detail: Route("detail/{gameId}") {
        fun createRoute(gameId: Int) = "detail/$gameId"
    }
    data object Favorite: Route("Favorite")
    data object Settings: Route("settings")
}
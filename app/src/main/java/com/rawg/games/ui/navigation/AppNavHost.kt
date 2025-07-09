package com.rawg.games.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.rawg.games.ui.screens.detail.DetailScreen
import com.rawg.games.ui.screens.favorite.FavoriteScreen
import com.rawg.games.ui.screens.home.HomeScreen
import com.rawg.games.ui.screens.settings.SettingsScreen

@Composable
fun AppNavHost(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Route.Home.route,
        modifier = modifier
    ) {
        composable(
            route = Route.Home.route
        ) {
            HomeScreen(
                navigateToDetail = { gameId ->
                    navHostController.navigate(Route.Detail.createRoute(gameId))
                }
            )
        }
        composable(
            route = Route.Detail.route,
            arguments = listOf(navArgument("gameId") {type = NavType.IntType})
        ) {
            val gameId = it.arguments?.getInt("gameId") ?: return@composable

            DetailScreen(
                gameId = gameId,
            )
        }
        composable(
            route = Route.Favorite.route
        ) {
            FavoriteScreen()
        }
        composable(
            route = Route.Settings.route
        ) {
            SettingsScreen()
        }
    }
}
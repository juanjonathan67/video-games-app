package com.rawg.games.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.rawg.games.ui.screens.favorite.FavoriteScreen
import com.rawg.games.ui.screens.home.HomeScreen
import com.rawg.games.ui.screens.search.SearchScreen
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
            HomeScreen()
        }
        composable(
            route = Route.Search.route
        ) {
            SearchScreen()
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
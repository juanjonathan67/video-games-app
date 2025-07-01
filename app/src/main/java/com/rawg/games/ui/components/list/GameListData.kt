package com.rawg.games.ui.components.list

data class GameListData(
    val id: Int,
    val title: String,
    val imageUrl: String,
    val metacritic: Int,
    val userRating: Double,
    val genres: List<String>,
)
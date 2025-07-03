package com.rawg.games.data.model


data class GameData(
    val id: Int,
    val name: String,
    val imageUrl: String?,
    val userRating: Double,
    val ratingsCount: Int,
    val metacritic: Int,
    val platforms: List<String>,
    val genres: List<String>,
)
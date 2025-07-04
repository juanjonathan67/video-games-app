package com.rawg.games.data.model

import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.platform.Platform


data class GameData(
    val id: Int,
    val name: String,
    val released: String,
    val imageUrl: String?,
    val userRating: Double,
    val ratingsCount: Int,
    val metacritic: Int,
    val platforms: List<Platform>,
    val genres: List<Genre>,
)
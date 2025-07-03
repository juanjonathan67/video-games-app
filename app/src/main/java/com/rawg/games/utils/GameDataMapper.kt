package com.rawg.games.utils

import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.service.games.GamesResponse

object GameDataMapper {
    fun transform(games: GamesResponse.Games): GameData {
        return games.let {
            GameData(
                id = it.id,
                name = it.name,
                imageUrl = it.imageUrl,
                userRating = it.userRating,
                metacritic = it.metacritic ?: 0,
                platforms = it.platforms.map { platform ->
                    platform.platformDetails.name
                },
                genres = it.genres.map { genre ->
                    genre.name
                },
            )
        }
    }
}
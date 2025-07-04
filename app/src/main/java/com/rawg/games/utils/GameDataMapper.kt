package com.rawg.games.utils

import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.platform.Platform

object GameDataMapper {
    fun transform(games: GamesResponse.Games): GameData {
        return games.let {
            GameData(
                id = it.id,
                name = it.name,
                released = games.released?.toFormattedDateString() ?: "unknown",
                imageUrl = it.imageUrl,
                userRating = it.userRating,
                ratingsCount = it.ratingsCount,
                metacritic = it.metacritic ?: 0,
                platforms = it.platforms.mapNotNull { platform ->
                    Platform.fromKey(platform.platformDetails.id)
                },
                genres = it.genres.mapNotNull { genre ->
                    Genre.fromKey(genre.id)
                },
            )
        }
    }
}
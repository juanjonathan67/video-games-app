package com.rawg.games.utils.mapper

import com.rawg.games.data.network.service.games.response.GameDetailsResponse
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.platform.Platform
import com.rawg.games.utils.toFormattedDateString

object GameDetailsMapper {
    fun transform(gameDetails: GameDetailsResponse) : GameDetails {
        return gameDetails.let {
            GameDetails(
                id = it.id,
                name = it.name,
                released = it.released?.toFormattedDateString() ?: "unknown",
                description = it.description,
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
                publishers = it.publishers.map { publisher ->
                    publisher.name
                },
                trailers = emptyList(),
                redditPosts = emptyList(),
            )
        }
    }
}
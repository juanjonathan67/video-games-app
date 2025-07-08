package com.rawg.games.domain.usecase

import com.rawg.games.domain.model.GameDetails
import com.rawg.games.domain.repository.GamesRepository
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class GetGameDetailsUseCase @Inject constructor(
    private val gamesRepository: GamesRepository,
) {
    operator fun invoke(id: Int): Single<GameDetails> {
        return Single.zip(
            gamesRepository.getGameDetails(id),
            gamesRepository.getGameTrailers(id),
            gamesRepository.getGameRedditPosts(id),
            ::Triple
        ).map { (gameDetails, gameTrailers, gameRedditPosts) ->
            gameDetails.copy(
                trailers = gameTrailers,
                redditPosts = gameRedditPosts,
            )
        }
    }
}
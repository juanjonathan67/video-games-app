package com.rawg.games.utils.mapper

import com.rawg.games.data.network.service.games.response.GameTrailersResponse
import com.rawg.games.domain.model.GameTrailer

object GameTrailersMapper {
    fun transform(gameTrailers: GameTrailersResponse.Trailer): GameTrailer {
        return gameTrailers.let {
            GameTrailer(
                id = it.id,
                name = it.name,
                resolution480 = it.data.resolution480,
                resolutionMax = it.data.resolutionMax,
            )
        }
    }
}
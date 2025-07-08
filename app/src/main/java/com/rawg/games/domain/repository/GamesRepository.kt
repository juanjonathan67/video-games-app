package com.rawg.games.domain.repository

import androidx.paging.PagingData
import com.rawg.games.domain.model.GameData
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.domain.model.GameRedditPost
import com.rawg.games.domain.model.GameTrailer
import com.rawg.games.ui.components.filter.ordering.Ordering
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single


interface GamesRepository {
    fun getGames(
        search: String? = null,
        ordering: Ordering? = null,
        genres: String? = null,
        platforms: String? = null,
    ) : Observable<PagingData<GameData>>

    fun getGameDetails(
        id: Int,
    ) : Single<GameDetails>

    fun getGameTrailers(
        id: Int,
    ): Single<List<GameTrailer>>

    fun getGameRedditPosts(
        id: Int,
    ): Single<List<GameRedditPost>>
}
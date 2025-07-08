package com.rawg.games.data.network.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.observable
import com.rawg.games.domain.model.GameData
import com.rawg.games.data.network.paging.GamesPagingSource
import com.rawg.games.domain.repository.GamesRepository
import com.rawg.games.data.network.service.games.GamesService
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.domain.model.GameRedditPost
import com.rawg.games.domain.model.GameTrailer
import com.rawg.games.utils.mapper.GameDataMapper
import com.rawg.games.ui.components.filter.ordering.Ordering
import com.rawg.games.utils.mapper.GameDetailsMapper
import com.rawg.games.utils.mapper.GameRedditPostsMapper
import com.rawg.games.utils.mapper.GameTrailersMapper
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesService: GamesService,
) : GamesRepository {
    override fun getGames(
        search: String?,
        ordering: Ordering?,
        genres: String?,
        platforms: String?,
    ): Observable<PagingData<GameData>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = ENABLE_PLACEHOLDERS,
                maxSize = MAX_SIZE,
            ),
            pagingSourceFactory = { GamesPagingSource(
                gamesService, search, ordering, genres, platforms
            ) }
        )
            .observable
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                it.map { game ->
                    GameDataMapper.transform(game)
                }
            }
    }

    override fun getGameDetails(id: Int): Single<GameDetails> {
        return gamesService
            .getGameDetails(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { GameDetailsMapper.transform(it) }
    }

    override fun getGameTrailers(id: Int): Single<List<GameTrailer>> {
        return gamesService
            .getGameTrailers(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { gameTrailersResponse ->
                gameTrailersResponse.results.map {
                    GameTrailersMapper.transform(it)
                }
            }

    }

    override fun getGameRedditPosts(id: Int): Single<List<GameRedditPost>> {
        return gamesService
            .getGameRedditPosts(id)
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map { gameRedditPostsResponse ->
                gameRedditPostsResponse.results.map {
                    GameRedditPostsMapper.transform(it)
                }
            }
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
        private const val MAX_SIZE = 200
    }
}
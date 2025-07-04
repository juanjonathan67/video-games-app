package com.rawg.games.data.network.repository.impl

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import androidx.paging.rxjava3.observable
import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.paging.GamesPagingSource
import com.rawg.games.data.network.repository.GamesRepository
import com.rawg.games.data.network.service.games.GamesService
import com.rawg.games.utils.GameDataMapper
import com.rawg.games.ui.components.filter.ordering.Ordering
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import javax.inject.Inject

class GamesRepositoryImpl @Inject constructor(
    private val gamesService: GamesService,
) : GamesRepository {
    override fun getGames(
        search: String?,
        ordering: Ordering?
    ): Observable<PagingData<GameData>> {
        return Pager(
            config = PagingConfig(
                pageSize = PAGE_SIZE,
                enablePlaceholders = ENABLE_PLACEHOLDERS,
                maxSize = MAX_SIZE,
            ),
            pagingSourceFactory = { GamesPagingSource(gamesService, search, ordering) }
        )
            .observable
            .observeOn(Schedulers.computation())
            .map {
                it.map { game ->
                    GameDataMapper.transform(game)
                }
            }
    }

    companion object {
        private const val PAGE_SIZE = 20
        private const val ENABLE_PLACEHOLDERS = false
        private const val MAX_SIZE = 200
    }
}
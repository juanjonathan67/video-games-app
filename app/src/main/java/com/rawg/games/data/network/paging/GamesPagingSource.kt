package com.rawg.games.data.network.paging

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.data.network.service.games.GamesService
import com.rawg.games.ui.components.filter.ordering.Ordering
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GamesPagingSource(
    private val gamesService: GamesService,
    private val search: String?,
    private val ordering: Ordering?,
    private val genres: String?,
    private val platforms: String?,
) : RxPagingSource<Int, GamesResponse.Games>(){
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GamesResponse.Games>> {
        val position = params.key ?: 1

        return gamesService.getGames(
            page = position,
            pageSize = params.loadSize,
            search = search,
            ordering = ordering.toString(),
            genres = genres,
            platforms = platforms,
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map<LoadResult<Int, GamesResponse.Games>> {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (position <= 1) null else position.dec(),
                    nextKey = if (it.next == null) null else position.inc(),
                )
            }
            .onErrorReturn {
                LoadResult.Error(it)
            }
    }

    override fun getRefreshKey(state: PagingState<Int, GamesResponse.Games>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }
}
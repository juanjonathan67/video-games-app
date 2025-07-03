package com.rawg.games.data.network.paging

import android.util.Log
import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.data.network.service.games.GamesService
import com.rawg.games.utils.Ordering
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

class GamesPagingSource(
    private val gamesService: GamesService,
    private val search: String?,
    private val ordering: Ordering?,
) : RxPagingSource<Int, GamesResponse.Games>(){
    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, GamesResponse.Games>> {
        val position = params.key ?: 1

        Log.d("PagingSource", "Loading page $position")

        return gamesService.getGames(
            page = position,
            pageSize = PAGE_SIZE,
            search = search,
            ordering = ordering.toString(),
        )
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.computation())
            .map {
                LoadResult.Page(
                    data = it.results,
                    prevKey = if (position <= 1) null else position.dec(),
                    nextKey = if (it.next == null) null else position.inc(),
                )
            }
    }

    override fun getRefreshKey(state: PagingState<Int, GamesResponse.Games>): Int? {
        return state.anchorPosition?.let {
            val anchorPage = state.closestPageToPosition(it)
            anchorPage?.prevKey?.inc() ?: anchorPage?.nextKey?.dec()
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}
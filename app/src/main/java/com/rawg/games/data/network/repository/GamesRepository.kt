package com.rawg.games.data.network.repository

import androidx.paging.PagingData
import com.rawg.games.data.model.GameData
import com.rawg.games.ui.components.filter.ordering.Ordering
import io.reactivex.rxjava3.core.Observable


interface GamesRepository {
    fun getGames(
        search: String? = null,
        ordering: Ordering? = null,
        genres: String? = null,
        platforms: String? = null,
    ) : Observable<PagingData<GameData>>
}
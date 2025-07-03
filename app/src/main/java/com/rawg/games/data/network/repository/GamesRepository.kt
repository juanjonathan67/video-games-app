package com.rawg.games.data.network.repository

import androidx.paging.PagingData
import com.rawg.games.data.model.GameData
import com.rawg.games.utils.Ordering
import io.reactivex.rxjava3.core.Observable


interface GamesRepository {
    fun getGames(
        search: String? = null,
        ordering: Ordering? = null,
    ) : Observable<PagingData<GameData>>
}
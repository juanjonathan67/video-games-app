package com.rawg.games.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.repository.GamesRepository
import com.rawg.games.ui.components.filter.Filter
import io.reactivex.rxjava3.core.Observable
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    private val filter = MutableStateFlow(Filter())

    val games: Flow<PagingData<GameData>> = searchQuery
        .combine(filter) { query, filter ->
            Pair(query, filter)
        }
        .flatMapLatest { (query, filter) ->
            getBestGames(query, filter).asFlow()
    }.cachedIn(viewModelScope)

    fun setSearchQuery(newSearchQuery: String) {
        searchQuery.value = newSearchQuery
    }

    fun setFilter(newFilter: Filter) {
        filter.value = newFilter
    }

    private fun getBestGames(
        searchQuery: String,
        filter: Filter,
    ): Observable<PagingData<GameData>> {
        return gamesRepository
            .getGames(
                search = searchQuery,
                ordering = filter.ordering
            )
    }
}
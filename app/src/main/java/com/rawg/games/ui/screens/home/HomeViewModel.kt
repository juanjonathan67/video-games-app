package com.rawg.games.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rawg.games.domain.model.GameData
import com.rawg.games.domain.repository.GamesRepository
import com.rawg.games.ui.components.filter.Filter
import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.platform.Platform
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    private val searchQuerySubject = BehaviorSubject.createDefault("")
    private val filterSubject = BehaviorSubject.createDefault(Filter())

    val games : Flow<PagingData<GameData>> = Observable
        .combineLatest(
            searchQuerySubject.hide(),
            filterSubject.hide(),
            ::Pair
        ).flatMap { (searchQuery, filter) ->
            getGames(searchQuery, filter)
        }
        .asFlow()
        .cachedIn(viewModelScope)

    fun setSearchQuery(newSearchQuery: String) {
        searchQuerySubject.onNext(newSearchQuery)
    }

    fun setFilter(newFilter: Filter) {
        filterSubject.onNext(newFilter)
    }

    private fun getGames(
        searchQuery: String,
        filter: Filter,
    ): Observable<PagingData<GameData>> {
        return gamesRepository
            .getGames(
                search = searchQuery.ifBlank { null },
                ordering = filter.ordering,
                genres = if (filter.genres.isNotEmpty()) {
                    Genre.transformToKeysString(filter.genres)
                } else {
                    null
                },
                platforms = if (filter.platforms.isNotEmpty()) {
                    Platform.transformToKeysString(filter.platforms)
                } else {
                    null
                },
            )
    }
}
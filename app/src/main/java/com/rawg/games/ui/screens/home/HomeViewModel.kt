package com.rawg.games.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.repository.GamesRepository
import com.rawg.games.utils.Ordering
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    private val searchQuery = MutableStateFlow("")
    val games: Flow<PagingData<GameData>> = searchQuery.flatMapLatest {
        getBestGames()
    }

    fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    fun getBestGames(): Flow<PagingData<GameData>> {
        return gamesRepository
            .getGames(
                search = searchQuery.value,
                ordering = Ordering.METACRITIC_DESCENDING
            )
            .asFlow()
            .cachedIn(viewModelScope)
    }
}
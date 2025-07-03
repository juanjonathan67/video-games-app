package com.rawg.games.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.repository.GamesRepository
import com.rawg.games.utils.Ordering
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

class HomeViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    fun getBestGames(): Flow<PagingData<GameData>> {
        return gamesRepository
            .getGames(ordering = Ordering.METACRITIC_DESCENDING)
            .asFlow()
            .cachedIn(viewModelScope)
    }
}
package com.rawg.games.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.rawg.games.data.model.GameData
import com.rawg.games.data.network.repository.GamesRepository
import com.rawg.games.utils.Ordering
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.rx3.asFlow
import javax.inject.Inject

class HomeScreenViewModel @Inject constructor(
    private val gamesRepository: GamesRepository,
) : ViewModel() {
    fun getBestGames(): Flow<PagingData<GameData>> {
        return gamesRepository
            .getGames(ordering = Ordering.METACRITIC_DESCENDING)
            .asFlow()
    }
}
package com.rawg.games.ui.screens.detail

import android.util.Log
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.ui.components.error.Error
import com.rawg.games.ui.components.loading.Loading
import com.rawg.games.utils.LocalViewModelFactory
import com.rawg.games.utils.Result
import io.reactivex.rxjava3.core.BackpressureStrategy
import kotlinx.coroutines.reactive.asFlow

@Composable
fun DetailScreen(
    gameId: Int,
    navigateBack: () -> Unit,
    detailViewModel: DetailViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    val gameDetailsFlow = remember {
        detailViewModel.gameDetailsSubject.toFlowable(BackpressureStrategy.LATEST).asFlow()
    }
    val gameDetailsState = gameDetailsFlow.collectAsState(initial = Result.Loading)

    LaunchedEffect(gameId) {
        detailViewModel.getGameDetails(gameId)
    }

    when (gameDetailsState.value) {
        is Result.Loading -> { Loading() }
        is Result.Error -> { Error() }
        is Result.Success<GameDetails> -> {
            val gameDetails = (gameDetailsState.value as Result.Success<GameDetails>).data
            DetailScreenContent(gameDetails)
            Log.d("Game Detail", gameDetails.toString())
        }
    }
}

@Composable
fun DetailScreenContent(
    gameDetails: GameDetails,
) {
    Text("Detail Screen")
}
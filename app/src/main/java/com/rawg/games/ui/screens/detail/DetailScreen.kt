package com.rawg.games.ui.screens.detail

import android.util.Log
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.rawg.games.R
import com.rawg.games.domain.model.GameDetails
import com.rawg.games.ui.components.error.Error
import com.rawg.games.ui.components.genre.GenreList
import com.rawg.games.ui.components.html.HtmlView
import com.rawg.games.ui.components.image.GameImage
import com.rawg.games.ui.components.loading.Loading
import com.rawg.games.ui.components.platform.PlatformList
import com.rawg.games.ui.components.reddit.RedditPost
import com.rawg.games.ui.components.video.VideoPlayer
import com.rawg.games.utils.LocalViewModelFactory
import com.rawg.games.utils.Result
import com.rawg.games.utils.getScreenSize
import com.rawg.games.utils.scaleSize
import io.reactivex.rxjava3.core.BackpressureStrategy
import kotlinx.coroutines.reactive.asFlow

@Composable
fun DetailScreen(
    gameId: Int,
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
    modifier: Modifier = Modifier,
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(4.dp),
        modifier = modifier
            .padding(bottom = 4.dp)
            .verticalScroll(rememberScrollState())
    ) {
        if (gameDetails.trailers.isNotEmpty()) {
            VideoPlayer(
                gameDetails.trailers[(0..< gameDetails.trailers.size).random()].resolution480,
                modifier = modifier
                    .height(getScreenSize().second.scaleSize(0.25))
            )
        } else {
            GameImage(
                gameDetails.imageUrl,
                imageHeight = getScreenSize().second.scaleSize(0.25)
            )
        }

        Column(
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier
                .padding(horizontal = 4.dp)
        ) {
            Text(
                text = gameDetails.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            HtmlView(
                html = gameDetails.description,
            )

            Text(
                stringResource(R.string.published_by, gameDetails.publishers.joinToString(", ")),
                fontSize = 14.sp,
            )
            Text(
                stringResource(R.string.release_date, gameDetails.released),
                fontSize = 14.sp,
            )
            Text(
                stringResource(R.string.metacritic_rating, gameDetails.metacritic),
                fontSize = 14.sp,
            )
            Text(
                stringResource(R.string.user_rating, gameDetails.userRating, gameDetails.ratingsCount),
                fontSize = 14.sp,
            )
            GenreList(
                genreList = gameDetails.genres,
            )
            PlatformList(
                platforms = gameDetails.platforms,
            )
        }

        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier
                .padding(start = 4.dp, top = 4.dp)
                .horizontalScroll(rememberScrollState())
        ) {
            gameDetails.redditPosts.forEach {
                RedditPost(
                    it,
                    modifier = Modifier
                        .width(getScreenSize().first.scaleSize(0.8))
                        .height(getScreenSize().second.scaleSize(0.25))
                )
            }
        }
    }
}
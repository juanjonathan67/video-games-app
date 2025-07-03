package com.rawg.games.ui.screens.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.rawg.games.R
import com.rawg.games.data.model.GameData
import com.rawg.games.ui.components.list.GameListItem
import com.rawg.games.utils.LocalViewModelFactory
import kotlinx.coroutines.flow.flowOf

@Composable
fun HomeScreen(
    homeScreenViewModel: HomeScreenViewModel = viewModel(factory = LocalViewModelFactory.current)
) {
    val bestGames = homeScreenViewModel.getBestGames().collectAsLazyPagingItems()

    HomeScreenContent(bestGames)
}

@Composable
fun HomeScreenContent(
    bestGames: LazyPagingItems<GameData>,
) {
    Column {
        Text(
            stringResource(R.string.best_games_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

        LazyColumn (
            verticalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(4.dp),
        ) {
            items(
                count = bestGames.itemCount,
                key = { bestGames[it]?.id ?: it },
            ) {
                val item = bestGames[it] ?: return@items

                GameListItem(
                    gameData = item,
                    onClick = { }
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
fun HomeScreenContentPreview() {
    val list = listOf(
        GameData(
            id = 1,
            name = "The Legend of Zelda: Ocarina of Time",
            imageUrl = "https://media.rawg.io/media/games/3a0/3a0c8e9ed3a711c542218831b893a0fa.jpg",
            metacritic = 99,
            userRating = 4.38,
            platforms = listOf("PC", "Playstation"),
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameData(
            id = 2,
            name = "Baldur's Gate III",
            imageUrl = "https://media.rawg.io/media/games/699/69907ecf13f172e9e144069769c3be73.jpg",
            metacritic = 97,
            userRating = 4.44,
            platforms = listOf("PC", "Playstation"),
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameData(
            id = 3,
            name = "Metroid Prime",
            imageUrl = "https://media.rawg.io/media/games/c86/c86bc047ba949959a90fe24209d59439.jpg",
            metacritic = 97,
            userRating = 4.37,
            platforms = listOf("PC", "Playstation"),
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        )
    )

    HomeScreenContent(
        bestGames = flowOf(PagingData.from(list)).collectAsLazyPagingItems(),
    )
}
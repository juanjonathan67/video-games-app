package com.rawg.games.ui.screens.home

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.rawg.games.R
import com.rawg.games.ui.components.list.GameListData
import com.rawg.games.ui.components.list.GameListItem

@Composable
internal fun HomeScreen() {
    val list = listOf(
        GameListData(
            id = 1,
            title = "The Legend of Zelda: Ocarina of Time",
            imageUrl = "https://media.rawg.io/media/games/3a0/3a0c8e9ed3a711c542218831b893a0fa.jpg",
            metacritic = 99,
            userRating = 4.38,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameListData(
            id = 2,
            title = "Baldur's Gate III",
            imageUrl = "https://media.rawg.io/media/games/699/69907ecf13f172e9e144069769c3be73.jpg",
            metacritic = 97,
            userRating = 4.44,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameListData(
            id = 3,
            title = "Metroid Prime",
            imageUrl = "https://media.rawg.io/media/games/c86/c86bc047ba949959a90fe24209d59439.jpg",
            metacritic = 97,
            userRating = 4.37,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        )
    )

    HomeScreenContent(list)
}

@Composable
internal fun HomeScreenContent(
    gameListDatas: List<GameListData>,
) {
    Column {
        Text(
            stringResource(R.string.best_games_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

        LazyRow (
            contentPadding = PaddingValues(4.dp)
        ) {
            items(gameListDatas) {
                GameListItem(
                    it.id,
                    it.title,
                    it.imageUrl,
                    it.metacritic,
                    it.userRating,
                    it.genres,
                    onClick = { Log.d("Game Id", it.toString()) }
                )
            }
        }

        Text(
            stringResource(R.string.newest_games_title),
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(4.dp)
        )

        LazyRow (
            contentPadding = PaddingValues(4.dp)
        ) {
            items(gameListDatas) {
                GameListItem(
                    it.id,
                    it.title,
                    it.imageUrl,
                    it.metacritic,
                    it.userRating,
                    it.genres,
                    onClick = { Log.d("Game Id", it.toString()) }
                )
            }
        }
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
internal fun HomeScreenContentPreview() {
    val list = listOf(
        GameListData(
            id = 1,
            title = "The Legend of Zelda: Ocarina of Time",
            imageUrl = "https://media.rawg.io/media/games/3a0/3a0c8e9ed3a711c542218831b893a0fa.jpg",
            metacritic = 99,
            userRating = 4.38,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameListData(
            id = 2,
            title = "Baldur's Gate III",
            imageUrl = "https://media.rawg.io/media/games/699/69907ecf13f172e9e144069769c3be73.jpg",
            metacritic = 97,
            userRating = 4.44,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        GameListData(
            id = 3,
            title = "Metroid Prime",
            imageUrl = "https://media.rawg.io/media/games/c86/c86bc047ba949959a90fe24209d59439.jpg",
            metacritic = 97,
            userRating = 4.37,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        )
    )

    HomeScreenContent(
        list
    )
}
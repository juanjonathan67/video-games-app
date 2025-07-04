package com.rawg.games.ui.components.game

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.SubcomposeAsyncImage
import com.rawg.games.R
import com.rawg.games.data.model.GameData
import com.rawg.games.ui.components.error.PlaceholderImage
import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.genre.GenreList
import com.rawg.games.ui.components.loading.Loading
import com.rawg.games.ui.components.platform.Platform
import com.rawg.games.ui.components.platform.PlatformList
import com.rawg.games.utils.getScreenSize
import com.rawg.games.utils.scaleSize

@Composable
internal fun GameItem(
    gameData: GameData,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
){
    val screenSize = getScreenSize()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.card_elevation)
        ),
        onClick = { onClick(gameData.id) },
        modifier = modifier
            .fillMaxWidth()
    ) {
        SubcomposeAsyncImage(
            model = gameData.imageUrl,
            loading = {
                Loading()
            },
            error = {
                PlaceholderImage(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                )
            },
            contentDescription = "Game image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(screenSize.second.scaleSize(0.2))
        )

        Column {
            Text(
                text = gameData.name,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                stringResource(R.string.release_date, gameData.released),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                stringResource(R.string.metacritic_rating, gameData.metacritic),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                stringResource(R.string.user_rating, gameData.userRating, gameData.ratingsCount),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            GenreList(
                genreList = gameData.genres,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            )
            PlatformList(
                platforms = gameData.platforms,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
internal fun GameItemPreview(){
    GameItem(
        gameData = GameData(
            id = 1,
            name = "The Legend of Zelda: Ocarina of Time",
            released = "22 January 1999",
            imageUrl = "https://media.rawg.io/media/games/3a0/3a0c8e9ed3a711c542218831b893a0fa.jpg",
            metacritic = 99,
            userRating = 4.38,
            ratingsCount = 51355,
            platforms = Platform.entries.toList(),
            genres = Genre.entries.toList(),
        ),
        onClick = {},
    )
}

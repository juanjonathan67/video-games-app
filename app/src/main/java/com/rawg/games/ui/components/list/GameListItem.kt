package com.rawg.games.ui.components.list

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
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
import com.rawg.games.ui.components.genre.GenreList
import com.rawg.games.utils.getScreenSize
import com.rawg.games.utils.scaleSize

@Composable
internal fun GameListItem(
    gameListData: GameListData,
    onClick: (id: Int) -> Unit,
    modifier: Modifier = Modifier,
){
    val screenSize = getScreenSize()

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = dimensionResource(R.dimen.card_elevation)
        ),
        onClick = { onClick(gameListData.id) },
        modifier = modifier
            .width(screenSize.first.scaleSize(0.8))
    ) {
        SubcomposeAsyncImage(
            model = gameListData.imageUrl,
            loading = {
                CircularProgressIndicator(Modifier.size(40.dp))
            },
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(screenSize.second.scaleSize(0.2))
        )

        Column {
            Text(
                text = gameListData.title,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                stringResource(R.string.metacritic_rating, gameListData.metacritic),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            Text(
                stringResource(R.string.user_rating, gameListData.userRating),
                fontSize = 14.sp,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 2.dp)
            )
            GenreList(
                genreList = gameListData.genres,
                modifier = Modifier
                    .padding(horizontal = 4.dp, vertical = 4.dp)
            )
        }
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
internal fun GameListItemPreview(){
    GameListItem(
        gameListData = GameListData(
            id = 1,
            title = "The Legend of Zelda: Ocarina of Time",
            imageUrl = "https://media.rawg.io/media/games/3a0/3a0c8e9ed3a711c542218831b893a0fa.jpg",
            metacritic = 99,
            userRating = 4.38,
            genres = listOf("Action", "RPG", "FPS", "Platformer", "Platformer", "Platformer", "Platformer")
        ),
        onClick = {},
    )
}

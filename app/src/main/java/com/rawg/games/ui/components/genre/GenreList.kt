package com.rawg.games.ui.components.genre

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun GenreList(
    genreList: List<Genre>,
    modifier: Modifier = Modifier,
    maxLines: Int = Int.MAX_VALUE,
    clickable: Boolean = false,
    onGenreClick: (Genre) -> Unit = {},
) {
    FlowRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxLines = maxLines,
        modifier = modifier
    ) {
        genreList.forEach {
            GenreLabel(
                genre = it,
                clickable = clickable,
                onGenreClick = onGenreClick,
            )
        }
    }
}

@Composable
internal fun GenreLabel(
    genre: Genre,
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    onGenreClick: (Genre) -> Unit = {},
) {
    var clicked by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                RoundedCornerShape(2.dp)
            )
            .padding(horizontal = 4.dp, vertical = 0.dp)
            .clickable(
                enabled = clickable,
                onClick = {
                    onGenreClick(genre)
                    clicked = !clicked
                },
            )
    ) {
        Text(
            text = genre.displayName,
            fontSize = 10.sp,
        )

        if (clicked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = null,
                modifier = modifier
                    .padding(horizontal = 2.dp)
            )
        }
    }

}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
internal fun GenreListPreview(){
    GenreList(
        genreList = Genre.entries.toList(),
        maxLines = Int.MAX_VALUE
    )
}
package com.rawg.games.ui.components.genre

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
internal fun GenreList(
    genreList: List<String>,
    modifier: Modifier = Modifier,
) {
    FlowRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        maxLines = 1,
        modifier = modifier
    ) {
        genreList.forEach {
            GenreLabel(it)
        }
    }
}

@Composable
internal fun GenreLabel(
    genre: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = genre,
        fontSize = 10.sp,
        modifier = modifier
            .border(
                BorderStroke(1.dp, Color.Black),
                RoundedCornerShape(2.dp)
            )
            .padding(horizontal = 4.dp, vertical = 0.dp)
    )
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
internal fun GenreListPreview(){
    GenreList(listOf(
        "Action",
        "RPG",
        "FPS",
        "Platformer",
        "Action",
        "RPG",
        "FPS",
        "Platformer",
        "Action",
        "RPG",
        "FPS",
        "Platformer"
    ))
}
package com.rawg.games.ui.components.image

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import coil3.compose.SubcomposeAsyncImage
import com.rawg.games.ui.components.error.PlaceholderImage
import com.rawg.games.ui.components.loading.Loading

@Composable
fun GameImage(
    imageUrl: String?,
    imageHeight: Dp,
    modifier: Modifier = Modifier,
    contentDescription: String? = null,
) {
    SubcomposeAsyncImage(
        model = imageUrl,
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
        contentDescription = contentDescription,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(imageHeight)
    )
}
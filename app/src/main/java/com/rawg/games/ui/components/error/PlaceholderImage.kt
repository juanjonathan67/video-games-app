package com.rawg.games.ui.components.error

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.rawg.games.R

@Composable
fun PlaceholderImage(
    modifier: Modifier = Modifier,
    contentScale: ContentScale = ContentScale.Crop
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Image(
            painter = painterResource(R.drawable.no_image_placeholder_24),
            contentDescription = "Placeholder",
            contentScale = contentScale,
            modifier = Modifier
                .weight(1F)
        )
        
        Text(stringResource(R.string.no_image_text))
    }
}

@Composable
@Preview(showBackground = true)
fun PlaceholderImagePreview() {
    PlaceholderImage()
}
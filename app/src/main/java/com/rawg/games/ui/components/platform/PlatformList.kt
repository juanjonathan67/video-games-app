package com.rawg.games.ui.components.platform

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PlatformList(
    platforms: List<Platform>,
    modifier: Modifier = Modifier,
    maxLines: Int = 1,
    clickable: Boolean = false,
    onPlatformClick: (Int) -> Unit = {},
) {
    FlowRow (
        horizontalArrangement = Arrangement.spacedBy(4.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp),
        maxLines = maxLines,
        modifier = modifier
    ) {
        platforms.forEach {
            PlatformLabel(it)
        }
    }
}

@Composable
internal fun PlatformLabel(
    platform: Platform,
    modifier: Modifier = Modifier,
    clickable: Boolean = false,
    onPlatformClick: (Int) -> Unit = {},
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .height(32.dp)
            .border(BorderStroke(1.dp, Color.Black), RoundedCornerShape(2.dp))
            .padding(4.dp)
            .clickable(
                enabled = clickable,
                onClick = { onPlatformClick(platform.key) }
            )
    ) {
        Image(
            painter = painterResource(platform.iconId),
            contentDescription = platform.name
        )

        Text(
            text = platform.name,
            fontSize = 10.sp,
            modifier = modifier
                .padding(horizontal = 2.dp)
        )
    }
}

@Composable
@Preview(widthDp = 360, heightDp = 640, showBackground = true)
fun PlatformListPreview() {
    PlatformList(
        Platform.entries,
        maxLines = Int.MAX_VALUE,
    )
}
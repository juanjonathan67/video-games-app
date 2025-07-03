package com.rawg.games.ui.components.loading

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun Loading(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicator(
        modifier
            .fillMaxSize()
            .wrapContentSize(Alignment.Center)
    )
}

@Composable
@Preview(showBackground = true)
fun LoadingPreview() {
    Loading()
}
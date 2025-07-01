package com.rawg.games.utils

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.times

@Composable
fun getScreenSize(): Pair<Dp, Dp> {
    val configuration = LocalConfiguration.current
    return Pair(configuration.screenWidthDp.dp, configuration.screenHeightDp.dp)
}

fun Dp.scaleSize(scale: Double) = (scale * this)
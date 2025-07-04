package com.rawg.games.ui.components.filter

import com.rawg.games.ui.components.filter.ordering.Ordering
import com.rawg.games.ui.components.genre.Genre
import com.rawg.games.ui.components.platform.Platform

data class Filter(
    val ordering: Ordering = Ordering.METACRITIC_DESCENDING,
    val platforms: List<Platform> = emptyList(),
    val genres: List<Genre> = emptyList(),
)

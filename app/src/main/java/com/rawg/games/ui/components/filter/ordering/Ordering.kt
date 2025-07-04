package com.rawg.games.ui.components.filter.ordering

enum class Ordering(val key: String, val displayName: String) {
    RATING_ASCENDING(key = "rating", displayName = "User Rating (Ascending)"),
    RATING_DESCENDING(key = "-rating", displayName = "User Rating (Descending)"),
    METACRITIC_ASCENDING(key = "metacritic", displayName = "Metacritic Rating (Ascending)"),
    METACRITIC_DESCENDING(key = "-metacritic", displayName = "Metacritic Rating (Descending)"),
    RELEASED_ASCENDING(key = "released", displayName = "Release Date (Ascending)"),
    RELEASED_DESCENDING(key = "-released", displayName = "Release Date (Descending)");

    override fun toString(): String {
        return key
    }
}
package com.rawg.games.utils

enum class Ordering(private val key: String) {
    RATING_ASCENDING("rating"),
    RATING_DESCENDING("-rating"),
    METACRITIC_ASCENDING("metacritic"),
    METACRITIC_DESCENDING("-metacritic"),
    RELEASED_ASCENDING("released"),
    RELEASED_DESCENDING("-released"), ;

    override fun toString(): String {
        return key
    }
}
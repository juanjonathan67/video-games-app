package com.rawg.games.domain.model

data class GameRedditPost(
    val id: Int,
    val title: String,
    val text: String,
    val url: String,
    val author: String,
    val created: String,
)
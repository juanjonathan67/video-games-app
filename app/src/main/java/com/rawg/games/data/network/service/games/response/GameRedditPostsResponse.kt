package com.rawg.games.data.network.service.games.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameRedditPostsResponse(
    @Json(name = "results")
    val results: List<RedditPost>

) {
    @JsonClass(generateAdapter = true)
    data class RedditPost(
        @Json(name = "id")
        val id: Int,

        @Json(name = "name")
        val title: String,

        @Json(name = "text")
        val text: String,

        @Json(name = "url")
        val url: String,

        @Json(name = "username")
        val author: String,

        @Json(name = "created")
        val created: String,
    )
}
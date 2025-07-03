package com.rawg.games.data.network.service.games

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GamesResponse (
    @Json(name = "next")
    val next: String?,

    @Json(name = "results")
    val results: List<Games>,
) {
    @JsonClass(generateAdapter = true)
    data class Games (
        @Json(name = "id")
        val id: Int,

        @Json(name = "name")
        val name: String,

        @Json(name = "background_image")
        val imageUrl: String?,

        @Json(name = "rating")
        val userRating: Double,

        @Json(name = "ratings_count")
        val ratingsCount: Int,

        @Json(name = "metacritic")
        val metacritic: Int?,

        @Json(name = "parent_platforms")
        val platforms: List<Platform>,

        @Json(name = "genres")
        val genres: List<Genre>,
    )

    @JsonClass(generateAdapter = true)
    data class Platform (
        @Json(name = "platform")
        val platformDetails: PlatformDetails,
    )

    @JsonClass(generateAdapter = true)
    data class PlatformDetails(
        @Json(name = "name")
        val name: String,
    )

    @JsonClass(generateAdapter = true)
    data class Genre(
        @Json(name = "name")
        val name: String,
    )
}

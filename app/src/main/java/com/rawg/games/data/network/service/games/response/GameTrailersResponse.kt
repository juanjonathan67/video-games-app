package com.rawg.games.data.network.service.games.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GameTrailersResponse(
    @Json(name = "results")
    val results: List<Trailer>

) {
    @JsonClass(generateAdapter = true)
    data class Trailer(
        @Json(name = "id")
        val id: Int,

        @Json(name = "name")
        val name: String,

        @Json(name = "data")
        val data: Data,
    )

    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "480")
        val resolution480: String,

        @Json(name = "max")
        val resolutionMax: String,
    )
}
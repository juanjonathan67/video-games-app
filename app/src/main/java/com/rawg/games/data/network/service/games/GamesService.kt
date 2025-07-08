package com.rawg.games.data.network.service.games

import com.rawg.games.data.network.service.games.response.GameDetailsResponse
import com.rawg.games.data.network.service.games.response.GameRedditPostsResponse
import com.rawg.games.data.network.service.games.response.GameTrailersResponse
import com.rawg.games.data.network.service.games.response.GamesResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GamesService {
    @GET("games")
    fun getGames(
        @Query("page") page: Int? = null,
        @Query("page_size") pageSize: Int? = null,
        @Query("search") search: String? = null,
        @Query("ordering") ordering: String? = null,
        @Query("genres") genres: String? = null,
        @Query("parent_platforms") platforms: String? = null,
    ): Single<GamesResponse>

    @GET("games/{id}")
    fun getGameDetails(
        @Path("id") id: Int,
    ): Single<GameDetailsResponse>

    @GET("games/{id}/movies")
    fun getGameTrailers(
        @Path("id") id: Int,
    ): Single<GameTrailersResponse>

    @GET("games/{id}/reddit")
    fun getGameRedditPosts(
        @Path("id") id: Int,
    ): Single<GameRedditPostsResponse>
}
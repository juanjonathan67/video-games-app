package com.rawg.games.data.network.service.games

import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
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
}
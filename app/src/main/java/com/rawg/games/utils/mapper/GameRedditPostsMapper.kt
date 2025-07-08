package com.rawg.games.utils.mapper

import com.rawg.games.data.network.service.games.response.GameRedditPostsResponse
import com.rawg.games.domain.model.GameRedditPost
import com.rawg.games.utils.toFormattedDateString

object GameRedditPostsMapper {
    fun transform(gameRedditPosts: GameRedditPostsResponse.RedditPost): GameRedditPost {
        return gameRedditPosts.let {
            GameRedditPost(
                id = it.id,
                title = it.title,
                text = it.text,
                url = it.url,
                author = it.author,
                created = it.created.toFormattedDateString(),
            )
        }
    }
}
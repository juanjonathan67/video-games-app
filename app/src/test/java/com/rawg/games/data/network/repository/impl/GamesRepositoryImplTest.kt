package com.rawg.games.data.network.repository.impl

import androidx.paging.testing.asSnapshot
import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.data.network.service.games.GamesService
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.rx3.asFlow
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GamesRepositoryImplTest {

    @Mock
    private lateinit var mockGamesService: GamesService

    private lateinit var repository: GamesRepositoryImpl

    @Before
    fun setup() {
        repository = GamesRepositoryImpl(mockGamesService)
    }

    @Test
    fun gamesRepository_getGames_returnsGameData() = runTest {
        // setup
        val rawGames = listOf(
            GamesResponse.Games(
                id = 1,
                name = "Accepted Game",
                released = "2025-04-02",
                imageUrl = "url1",
                userRating = 4.5,
                ratingsCount = 150,
                metacritic = 90,
                platforms = listOf(GamesResponse.Platform(GamesResponse.PlatformDetails(id = 1))),
                genres = listOf(GamesResponse.Genre(id = 4))
            ),
            GamesResponse.Games(
                id = 2,
                name = "Filtered Out Game",
                released = "2025-04-02",
                imageUrl = "url2",
                userRating = 4.5,
                ratingsCount = 50,
                metacritic = 70,
                platforms = listOf(GamesResponse.Platform(GamesResponse.PlatformDetails(id = 1))),
                genres = listOf(GamesResponse.Genre(id = 4))
            )
        )

        val mockResponse = GamesResponse(
            next = null,
            results = rawGames
        )

        `when`(
            mockGamesService.getGames(any(), any(), any(), any())
        ).thenReturn(Single.just(mockResponse))

        // action
        val pagingData = repository.getGames(null, null)
        val result = pagingData.asFlow().asSnapshot()

        // assert
        Assert.assertEquals(2, result.size)
        Assert.assertEquals("Accepted Game", result[0].name)
    }
}
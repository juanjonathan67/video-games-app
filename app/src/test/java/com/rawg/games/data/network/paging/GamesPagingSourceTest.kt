package com.rawg.games.data.network.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.data.network.service.games.GamesService
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.any
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class GamesPagingSourceTest {
    @Mock
    private lateinit var mockGamesService: GamesService

    private lateinit var gamesPagingSource: GamesPagingSource

    @Before
    fun setUp() {
        gamesPagingSource = GamesPagingSource(
            gamesService = mockGamesService,
            search = null,
            ordering = null,
        )
    }

    @Test
    fun gamesPagingSource_testLoad_returnsPageOfGames() = runTest {
        // setup
        val gamesList = listOf(
            GamesResponse.Games(
                id = 1,
                name = "Test Game",
                released = "2025-04-02",
                imageUrl = "https://example.com/image.jpg",
                userRating = 4.5,
                ratingsCount = 100,
                metacritic = 88,
                platforms = listOf(
                    GamesResponse.Platform(
                        platformDetails = GamesResponse.PlatformDetails(id = 1)
                    )
                ),
                genres = listOf(
                    GamesResponse.Genre(name = "Action")
                )
            ),
            GamesResponse.Games(
                id = 2,
                name = "Test Game 2",
                released = "2025-04-02",
                imageUrl = "https://example.com/image2.jpg",
                userRating = 4.5,
                ratingsCount = 100,
                metacritic = 88,
                platforms = listOf(
                    GamesResponse.Platform(
                        platformDetails = GamesResponse.PlatformDetails(id = 1)
                    )
                ),
                genres = listOf(
                    GamesResponse.Genre(name = "Action")
                )
            ),
        )

        val mockResponse = GamesResponse(
            next = "https://api.rawg.io/games?page=2",
            results = gamesList
        )

        `when`(
            mockGamesService.getGames(any(), any(), any(), any())
        ).thenReturn(Single.just(mockResponse))

        // action
        val pager = TestPager(getPagingConfig(), gamesPagingSource)

        val result = pager.refresh() as PagingSource.LoadResult.Page

        // assert
        Assert.assertEquals(result.data, gamesList)
    }

    @Test
    fun gamesPagingSource_testLoadError_returnsError() = runTest {
        // setup
        `when`(
            mockGamesService.getGames(any(), any(), any(), any())
        ).thenReturn(Single.error(RuntimeException("Network error")))

        // action
        val pager = TestPager(getPagingConfig(), gamesPagingSource)

        val result = pager.refresh()
        // assert
        Assert.assertTrue(result is PagingSource.LoadResult.Error)

        // action
        val page = pager.getLastLoadedPage()
        Assert.assertNull(page)
    }

    private fun getPagingConfig(): PagingConfig {
        return PagingConfig(
            pageSize = 20,
            enablePlaceholders = false,
            maxSize = 200,
            prefetchDistance = 5,
            initialLoadSize = 20
        )
    }
}
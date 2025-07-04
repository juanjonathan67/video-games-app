package com.rawg.games.utils

import com.rawg.games.data.network.service.games.GamesResponse
import com.rawg.games.ui.components.platform.Platform
import org.junit.Assert.assertEquals
import org.junit.Test

class GameDataMapperTest {
    @Test
    fun transform_mapsGamesResponseToGameData_correctly() {
        // Arrange
        val response = GamesResponse.Games(
            id = 1,
            name = "Test Game",
            released = "2025-04-02",
            imageUrl = "https://example.com/image.jpg",
            userRating = 4.5,
            ratingsCount = 1200,
            metacritic = 88,
            platforms = listOf(
                GamesResponse.Platform(
                    platformDetails = GamesResponse.PlatformDetails(
                        id = 1
                    )
                ),
                GamesResponse.Platform(
                    platformDetails = GamesResponse.PlatformDetails(
                        id = 2
                    )
                ),
            ),
            genres = listOf(
                GamesResponse.Genre("Action"),
                GamesResponse.Genre("Adventure")
            )
        )

        // Act
        val result = GameDataMapper.transform(response)

        // Assert
        assertEquals(1, result.id)
        assertEquals("Test Game", result.name)
        assertEquals("https://example.com/image.jpg", result.imageUrl)
        assertEquals(4.5, result.userRating, 0.0)
        assertEquals(1200, result.ratingsCount)
        assertEquals(88, result.metacritic)
        assertEquals(listOf(Platform.PC, Platform.Playstation), result.platforms)
        assertEquals(listOf("Action", "Adventure"), result.genres)
    }

    @Test
    fun transform_setsMetacriticToZero_whenNull() {
        // Arrange
        val response = GamesResponse.Games(
            id = 2,
            name = "No Metacritic",
            released = "2025-04-02",
            imageUrl = "https://example.com/no_meta.jpg",
            userRating = 3.8,
            ratingsCount = 400,
            metacritic = null,
            platforms = listOf(
                GamesResponse.Platform(
                    platformDetails = GamesResponse.PlatformDetails(
                        id = 1
                    )
                ),
            ),
            genres = listOf(
                GamesResponse.Genre("Puzzle")
            )
        )

        // Act
        val result = GameDataMapper.transform(response)

        // Assert
        assertEquals(0, result.metacritic)
    }
}
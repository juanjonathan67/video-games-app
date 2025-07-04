package com.rawg.games.ui.components.genre

enum class Genre(val key: Int, val displayName: String) {
    Racing(key = 1, displayName = "Racing"),
    Shooter(key = 2, displayName = "Shooter"),
    Adventure(key = 3, displayName = "Adventure"),
    Action(key = 4, displayName = "Action"),
    Rpg(key = 5, displayName = "RPG"),
    Fighting(key = 6, displayName = "Fighting"),
    Puzzle(key = 7, displayName = "Puzzle"),
    Strategy(key = 10, displayName = "Strategy"),
    Arcade(key = 11, displayName = "Arcade"),
    Simulation(key = 14, displayName = "Simulation"),
    Sports(key = 15, displayName = "Sports"),
    Card(key = 17, displayName = "Card"),
    Family(key = 19, displayName = "Family"),
    BoardGames(key = 28, displayName = "Board Games"),
    Educational(key = 34, displayName = "Educational"),
    Casual(key = 40, displayName = "Casual"),
    Indie(key = 51, displayName = "Indie"),
    MassivelyMultiplayer(key = 59, displayName = "Massively Multiplayer"),
    Platformer(key = 83, displayName = "Platformer");

    companion object {
        private val keyMap = Genre.entries.associateBy { it.key }

        fun fromKey(key: Int): Genre? = keyMap[key]
    }
}
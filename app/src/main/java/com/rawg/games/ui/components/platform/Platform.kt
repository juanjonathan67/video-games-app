package com.rawg.games.ui.components.platform

import com.rawg.games.R

enum class Platform(val key: Int, val iconId: Int) {
    PC(key = 1, iconId = R.drawable.outline_personal_computer_24),
    Playstation(key = 2, iconId = R.drawable.outline_playstation_24),
    Xbox(key = 3, iconId = R.drawable.outline_xbox_24),
    IOS(key = 4, iconId = R.drawable.outline_apple_24),
    Mac(key = 5, iconId = R.drawable.outline_apple_24),
    Android(key = 8, iconId = R.drawable.outline_android_24),
    Linux(key = 6, iconId = R.drawable.outline_linux_24),
    Nintendo(key = 7, iconId = R.drawable.outline_nintendo_24),
    Atari(key = 9, iconId = R.drawable.outline_atari_24),
    Commodore(key = 10, iconId = R.drawable.outline_commodore_24),
    Sega(key = 11, iconId = R.drawable.outline_sega_24),
    Web(key = 14, iconId = R.drawable.outline_chrome_24);

    companion object {
        private val keyMap = entries.associateBy { it.key }

        fun fromKey(key: Int): Platform? = keyMap[key]
    }
}
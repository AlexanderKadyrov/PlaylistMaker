package com.practicum.playlistmaker.di

import com.practicum.playlistmaker.core.di.coreModule
import com.practicum.playlistmaker.features.player.di.playerFeatureModule
import com.practicum.playlistmaker.features.search.di.searchFeatureModule
import com.practicum.playlistmaker.features.settings.di.settingsFeatureModule
import org.koin.dsl.module

val appModule = module {
    includes(
        coreModule,
        searchFeatureModule,
        settingsFeatureModule,
        playerFeatureModule
    )
}
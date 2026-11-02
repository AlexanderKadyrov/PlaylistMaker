package com.practicum.playlistmaker

import com.practicum.playlistmaker.features.search.domain.TrackSearchRepository
import com.practicum.playlistmaker.features.settings.domain.SettingsThemeRepository

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        TrackSearchRepository.init(this)
        SettingsThemeRepository.init(this)
        SettingsThemeRepository.switchTheme(SettingsThemeRepository.isDarkTheme())
    }
}
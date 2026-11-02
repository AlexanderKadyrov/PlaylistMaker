package com.practicum.playlistmaker

import com.practicum.playlistmaker.features.search.domain.TrackSearchRepository
import com.practicum.playlistmaker.features.settings.domain.ThemeRepository

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        TrackSearchRepository.init(this)
        ThemeRepository.init(this)
        ThemeRepository.switchTheme(ThemeRepository.isDarkTheme())
    }
}
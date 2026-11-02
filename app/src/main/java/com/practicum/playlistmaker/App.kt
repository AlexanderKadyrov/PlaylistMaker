package com.practicum.playlistmaker

import com.practicum.playlistmaker.features.track.domain.TrackRepository
import com.practicum.playlistmaker.features.settings.domain.ThemeRepository

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        TrackRepository.init(this)
        ThemeRepository.init(this)
        ThemeRepository.switchTheme(ThemeRepository.isDarkTheme())
    }
}
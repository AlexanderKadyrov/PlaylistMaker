package com.practicum.playlistmaker

import com.practicum.playlistmaker.repositories.TrackRepository
import com.practicum.playlistmaker.repositories.ThemeRepository

import android.app.Application

class App: Application() {

    override fun onCreate() {
        super.onCreate()
        TrackRepository.init(this)
        ThemeRepository.init(this)
        ThemeRepository.switchTheme(ThemeRepository.isDarkTheme())
    }
}
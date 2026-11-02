package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.di.Creator
import com.practicum.playlistmaker.features.settings.domain.SettingsRepository

class App: Application() {

    val creator: Creator by lazy {
        Creator(this)
    }

    override fun onCreate() {
        super.onCreate()
        SettingsRepository.init(this)
        SettingsRepository.switchTheme(SettingsRepository.isDarkTheme())
    }
}
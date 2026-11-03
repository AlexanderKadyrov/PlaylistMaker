package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.di.Creator
import com.practicum.playlistmaker.features.settings.di.settingsInteractor

class App : Application() {

    val creator: Creator by lazy {
        Creator(this)
    }

    override fun onCreate() {
        super.onCreate()
        creator.settingsInteractor().switchTheme()
    }
}
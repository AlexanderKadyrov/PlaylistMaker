package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.di.appModule
import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    private val settingsInteractor: SettingsInteractor by inject()

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
        settingsInteractor.switchTheme()
    }
}
package com.practicum.playlistmaker.features.settings.presentation.vm

import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor

class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor
) : ViewModel() {

    fun isDarkTheme(): Boolean {
        return settingsInteractor.isDarkTheme()
    }

    fun switchTheme(isDarkTheme: Boolean) {
        settingsInteractor.switchTheme(isDarkTheme)
    }
}
package com.practicum.playlistmaker.features.settings.domain.interactor.impl

import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor
import com.practicum.playlistmaker.features.settings.domain.repository.SettingsRepository

class SettingsInteractorImpl(
    private val settingsRepository: SettingsRepository
) : SettingsInteractor {

    override fun isDarkTheme(): Boolean {
        return settingsRepository.isDarkTheme()
    }

    override fun switchTheme(isDarkTheme: Boolean) {
        settingsRepository.switchTheme(isDarkTheme)
    }
}
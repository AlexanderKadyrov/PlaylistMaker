package com.practicum.playlistmaker.features.settings.domain.interactor

interface SettingsInteractor {
    fun isDarkTheme(): Boolean
    fun switchTheme(isDarkTheme: Boolean)
}
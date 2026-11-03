package com.practicum.playlistmaker.features.settings.domain.repository

interface SettingsRepository {
    fun isDarkTheme(): Boolean
    fun switchTheme(isDarkTheme: Boolean)
}
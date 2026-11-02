package com.practicum.playlistmaker.features.settings.data.repository

import androidx.appcompat.app.AppCompatDelegate
import com.practicum.playlistmaker.core.domain.storage.LocalStorage
import com.practicum.playlistmaker.features.settings.domain.repository.SettingsRepository

class SettingsRepositoryImpl(
    private val localStorage: LocalStorage
) : SettingsRepository {

    init {
        localStorage.registerOnChangeKeyValueListener(
            key = SHARED_PREFERENCES_THEME_KEY,
            callback = {
                applyTheme(isDarkTheme())
            }
        )
    }

    override fun isDarkTheme(): Boolean {
        return localStorage.getBoolean(SHARED_PREFERENCES_THEME_KEY)
    }

    override fun switchTheme(isDarkTheme: Boolean) {
        if (isDarkTheme == isDarkTheme()) {
            applyTheme(isDarkTheme)
        } else {
            localStorage.putBoolean(SHARED_PREFERENCES_THEME_KEY, isDarkTheme)
        }
    }

    private fun applyTheme(isDarkTheme: Boolean) {
        val mode =
            if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }

    private companion object {
        const val SHARED_PREFERENCES_THEME_KEY = "shared_preferences_theme_key"
    }
}
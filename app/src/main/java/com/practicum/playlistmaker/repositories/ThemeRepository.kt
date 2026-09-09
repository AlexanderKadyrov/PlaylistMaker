package com.practicum.playlistmaker.repositories

import android.content.SharedPreferences
import android.content.Context

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.edit

object ThemeRepository {

    private const val SHARED_PREFERENCES_THEME_REPOSITORY = "shared_preferences_theme_repository"
    private const val SHARED_PREFERENCES_THEME_KEY = "shared_preferences_theme_key"

    private lateinit var sharedPreferences: SharedPreferences
    private var sharedPreferencesListener = SharedPreferences.OnSharedPreferenceChangeListener { _, key ->
        if (key == SHARED_PREFERENCES_THEME_KEY) {
            applyTheme(isDarkTheme())
        }
    }

    fun init(context: Context) {
        sharedPreferences = context
            .applicationContext
            .getSharedPreferences(SHARED_PREFERENCES_THEME_REPOSITORY, Context.MODE_PRIVATE)
        sharedPreferences.registerOnSharedPreferenceChangeListener(sharedPreferencesListener)
    }

    fun isDarkTheme(): Boolean {
        return sharedPreferences.getBoolean(SHARED_PREFERENCES_THEME_KEY, false)
    }

    fun switchTheme(isDarkTheme: Boolean) {
        if (isDarkTheme == isDarkTheme()) {
            applyTheme(isDarkTheme)
        } else {
            sharedPreferences
                .edit {
                    putBoolean(SHARED_PREFERENCES_THEME_KEY, isDarkTheme)
                }
        }
    }

    private fun applyTheme(isDarkTheme: Boolean) {
        val mode = if (isDarkTheme) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
        AppCompatDelegate.setDefaultNightMode(mode)
    }
}
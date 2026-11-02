package com.practicum.playlistmaker.core.data.storage

import com.practicum.playlistmaker.core.domain.storage.LocalStorage
import android.content.Context
import androidx.core.content.edit

class LocalStorageImpl(
    context: Context
) : LocalStorage {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_REPOSITORY,
        Context.MODE_PRIVATE
    )

    override fun putString(key: String, value: String) {
        sharedPreferences
            .edit {
                putString(key, value)
            }
    }

    override fun getString(key: String): String? {
        return sharedPreferences.getString(key, null)
    }

    override fun putBoolean(key: String, value: Boolean) {
        sharedPreferences
            .edit {
                putBoolean(key, value)
            }
    }

    override fun getBoolean(key: String): Boolean {
        return sharedPreferences.getBoolean(key, false)
    }

    override fun remove(key: String) {
        sharedPreferences
            .edit {
                remove(key)
            }
    }

    private companion object {
        const val SHARED_PREFERENCES_REPOSITORY = "shared_preferences_repository"
    }
}
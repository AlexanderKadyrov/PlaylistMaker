package com.practicum.playlistmaker.core.data.storage

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.practicum.playlistmaker.core.domain.storage.LocalStorage

class LocalStorageImpl(
    context: Context
) : LocalStorage {

    private val sharedPreferences = context.getSharedPreferences(
        SHARED_PREFERENCES_REPOSITORY,
        Context.MODE_PRIVATE
    )

    private val listeners =
        mutableMapOf<String, SharedPreferences.OnSharedPreferenceChangeListener>()

    override fun unregisterOnChangeKeyValueListener(key: String) {
        listeners.remove(key)?.let { listener ->
            sharedPreferences.unregisterOnSharedPreferenceChangeListener(listener)
        }
    }

    override fun registerOnChangeKeyValueListener(key: String, callback: () -> Unit) {
        unregisterOnChangeKeyValueListener(key)
        val listener = SharedPreferences.OnSharedPreferenceChangeListener { _, thisKey ->
            if (key == thisKey) {
                callback()
            }
        }
        listeners[key] = listener
        sharedPreferences.registerOnSharedPreferenceChangeListener(listener)
    }

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
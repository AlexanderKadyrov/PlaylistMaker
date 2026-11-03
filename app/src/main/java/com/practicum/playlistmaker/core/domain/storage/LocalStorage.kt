package com.practicum.playlistmaker.core.domain.storage

interface LocalStorage {
    fun unregisterOnChangeKeyValueListener(key: String)
    fun registerOnChangeKeyValueListener(key: String, callback: () -> Unit)
    fun putString(key: String, value: String)

    fun getString(key: String): String?

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean

    fun remove(key: String)
}
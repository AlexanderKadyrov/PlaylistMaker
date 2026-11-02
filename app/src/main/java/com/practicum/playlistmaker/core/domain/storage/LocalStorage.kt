package com.practicum.playlistmaker.core.domain.storage

interface LocalStorage {
    fun putString(key: String, value: String)

    fun getString(key: String): String?

    fun putBoolean(key: String, value: Boolean)

    fun getBoolean(key: String): Boolean

    fun remove(key: String)
}
package com.practicum.playlistmaker.di

import android.content.Context
import com.practicum.playlistmaker.core.data.storage.LocalStorageImpl
import com.practicum.playlistmaker.core.domain.storage.LocalStorage


class Creator(
    private val context: Context
) {

    val localStorage: LocalStorage by lazy {
        LocalStorageImpl(context)
    }
}
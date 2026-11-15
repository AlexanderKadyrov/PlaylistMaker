package com.practicum.playlistmaker.di

import android.content.Context
import androidx.activity.ComponentActivity
import com.practicum.playlistmaker.App
import com.practicum.playlistmaker.core.data.storage.LocalStorageImpl
import com.practicum.playlistmaker.core.domain.storage.LocalStorage


class Creator(
    private val context: Context
) {

    val localStorage: LocalStorage by lazy {
        LocalStorageImpl(context)
    }
}

val ComponentActivity.creator: Creator
    get() = (applicationContext as App).creator
package com.practicum.playlistmaker.core.di

import com.practicum.playlistmaker.core.data.storage.LocalStorageImpl
import com.practicum.playlistmaker.core.domain.storage.LocalStorage
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val coreModule = module {
    single<LocalStorage> {
        LocalStorageImpl(context = androidContext())
    }
}
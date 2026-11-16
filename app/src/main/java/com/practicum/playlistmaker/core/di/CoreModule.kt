package com.practicum.playlistmaker.core.di

import com.google.gson.Gson
import com.practicum.playlistmaker.core.data.storage.LocalStorageImpl
import com.practicum.playlistmaker.core.domain.storage.LocalStorage
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val coreModule = module {
    single<LocalStorage> {
        LocalStorageImpl(context = androidContext())
    }

    single<Gson> {
        Gson()
    }

    single<OkHttpClient> {
        OkHttpClient
            .Builder()
            .build()
    }

    factory<Retrofit.Builder> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(
                GsonConverterFactory
                    .create(get())
            )
    }
}
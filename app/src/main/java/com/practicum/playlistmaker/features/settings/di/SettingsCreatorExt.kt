package com.practicum.playlistmaker.features.settings.di

import com.practicum.playlistmaker.di.Creator
import com.practicum.playlistmaker.features.settings.data.repository.SettingsRepositoryImpl
import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor
import com.practicum.playlistmaker.features.settings.domain.interactor.impl.SettingsInteractorImpl
import com.practicum.playlistmaker.features.settings.domain.repository.SettingsRepository

fun Creator.settingsInteractor(): SettingsInteractor {
    return SettingsInteractorImpl(
        settingsRepository = settingsRepository()
    )
}

private fun Creator.settingsRepository(): SettingsRepository {
    return SettingsRepositoryImpl(
        localStorage = localStorage
    )
}
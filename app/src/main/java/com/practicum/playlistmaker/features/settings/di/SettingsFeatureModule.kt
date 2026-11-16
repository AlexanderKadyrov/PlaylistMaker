package com.practicum.playlistmaker.features.settings.di

import com.practicum.playlistmaker.features.settings.data.repository.SettingsRepositoryImpl
import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor
import com.practicum.playlistmaker.features.settings.domain.interactor.impl.SettingsInteractorImpl
import com.practicum.playlistmaker.features.settings.domain.repository.SettingsRepository
import com.practicum.playlistmaker.features.settings.presentation.vm.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val settingsFeatureModule = module {

    single<SettingsRepository> {
        SettingsRepositoryImpl(
            localStorage = get()
        )
    }

    factory<SettingsInteractor> {
        SettingsInteractorImpl(
            settingsRepository = get()
        )
    }

    viewModel {
        SettingsViewModel(
            settingsInteractor = get()
        )
    }
}
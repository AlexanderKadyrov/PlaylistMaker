package com.practicum.playlistmaker.features.settings.presentation.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.features.settings.domain.interactor.SettingsInteractor

class SettingsViewModel(
    private val settingsInteractor: SettingsInteractor
) : ViewModel() {

    private val _state = MutableLiveData(settingsInteractor.isDarkTheme())
    val state: LiveData<Boolean> = _state

    fun switchTheme(isDarkTheme: Boolean) {
        settingsInteractor.switchTheme(isDarkTheme)
        _state.value = isDarkTheme
    }
}
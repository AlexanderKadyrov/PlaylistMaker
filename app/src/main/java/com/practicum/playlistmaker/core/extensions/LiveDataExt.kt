package com.practicum.playlistmaker.core.extensions

import androidx.lifecycle.MutableLiveData

inline fun <T> MutableLiveData<T>.update(block: (T) -> T) {
    value?.let { currentState ->
        value = block(currentState)
    }
}
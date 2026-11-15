package com.practicum.playlistmaker.core.extensions

import androidx.lifecycle.MutableLiveData

inline fun <T> MutableLiveData<T>.postUpdate(block: (T) -> T) {
    value?.let { currentState ->
        postValue(block(currentState))
    }
}
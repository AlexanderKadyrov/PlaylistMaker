package com.practicum.playlistmaker.core.extensions

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding

inline fun <T : ViewBinding> ViewGroup.inflateBinding(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T
): T {
    return inflater(LayoutInflater.from(context), this, false)
}
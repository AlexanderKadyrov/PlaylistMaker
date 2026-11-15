package com.practicum.playlistmaker.core.extensions

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding

fun RecyclerView.Adapter<*>.itemView(
    @LayoutRes resource: Int,
    parent: ViewGroup
): View {
    return LayoutInflater
        .from(parent.context)
        .inflate(resource, parent, false)
}

inline fun <T : ViewBinding> ViewGroup.inflateBinding(
    crossinline inflater: (LayoutInflater, ViewGroup, Boolean) -> T
): T {
    return inflater(LayoutInflater.from(context), this, false)
}
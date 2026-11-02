package com.practicum.playlistmaker.core.extensions

import androidx.recyclerview.widget.RecyclerView
import androidx.annotation.LayoutRes

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

fun RecyclerView.Adapter<*>.itemView(
    @LayoutRes resource: Int,
    parent: ViewGroup
): View {
    return LayoutInflater
        .from(parent.context)
        .inflate(resource, parent, false)
}
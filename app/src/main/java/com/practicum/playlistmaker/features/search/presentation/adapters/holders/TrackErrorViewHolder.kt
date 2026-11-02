package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import com.practicum.playlistmaker.R

import android.widget.TextView
import android.view.View

class TrackErrorViewHolder(
    itemView: View,
    private val onClick: (() -> Unit)?
): TrackViewHolder(itemView) {

    init {
        val refreshButton: TextView = itemView.findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
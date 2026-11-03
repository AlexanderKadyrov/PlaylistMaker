package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import com.practicum.playlistmaker.R

import android.widget.TextView
import android.view.View

class TrackSearchErrorViewHolder(
    itemView: View,
    private val onClick: (() -> Unit)?
): TrackSearchViewHolder(itemView) {

    init {
        val refreshButton: TextView = itemView.findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
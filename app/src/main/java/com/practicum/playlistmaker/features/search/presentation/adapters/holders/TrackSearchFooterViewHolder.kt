package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import com.practicum.playlistmaker.R

import android.widget.TextView
import android.view.View

class TrackSearchFooterViewHolder(
    itemView: View,
    private val onClick: (() -> Unit)?
): TrackSearchViewHolder(itemView) {

    init {
        val clearHistoryButton: TextView = itemView.findViewById(R.id.clearHistoryButton)
        clearHistoryButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
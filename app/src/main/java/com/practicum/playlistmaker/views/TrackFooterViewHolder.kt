package com.practicum.playlistmaker.views

import com.practicum.playlistmaker.R

import android.widget.TextView
import android.view.View

class TrackFooterViewHolder(
    itemView: View,
    private val onClick: (() -> Unit)?
): TrackViewHolder(itemView) {

    init {
        val clearHistoryButton: TextView = itemView.findViewById(R.id.clearHistoryButton)
        clearHistoryButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
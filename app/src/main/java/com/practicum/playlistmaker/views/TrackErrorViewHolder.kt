package com.practicum.playlistmaker.views

import com.practicum.playlistmaker.R

import android.widget.TextView
import android.view.View

class TrackErrorViewHolder(
    itemView: View,
    private val onRefreshClick: (() -> Unit)?
): TrackViewHolder(itemView) {

    init {
        val refreshButton: TextView = itemView.findViewById(R.id.refreshButton)
        refreshButton.setOnClickListener {
            onRefreshClick?.invoke()
        }
    }
}
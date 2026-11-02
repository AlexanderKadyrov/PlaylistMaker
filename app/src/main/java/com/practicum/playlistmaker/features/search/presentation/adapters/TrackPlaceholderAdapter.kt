package com.practicum.playlistmaker.features.search.presentation.adapters

import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackEmptyViewHolder
import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackErrorViewHolder
import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackViewHolder
import com.practicum.playlistmaker.core.extensions.itemView
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup

class TrackPlaceholderAdapter(
    private val viewState: ViewState,
    private val onRefreshClick: (() -> Unit)? = null
): RecyclerView.Adapter<TrackViewHolder>() {

    enum class ViewState {
        EMPTY,
        ERROR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return when (viewState) {
            ViewState.EMPTY -> {
                val itemView = itemView(R.layout.track_empty_view, parent)
                TrackEmptyViewHolder(itemView)
            }
            ViewState.ERROR -> {
                val itemView = itemView(R.layout.track_error_view, parent)
                TrackErrorViewHolder(itemView, onRefreshClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
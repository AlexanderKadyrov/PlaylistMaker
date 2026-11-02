package com.practicum.playlistmaker.features.search.presentation.adapters

import com.practicum.playlistmaker.core.extensions.itemView
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchEmptyViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchErrorViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchPlaceholderAdapter(
    private val viewState: com.practicum.playlistmaker.features.search.presentation.adapters.TrackPlaceholderAdapter.ViewState,
    private val onRefreshClick: (() -> Unit)? = null
): RecyclerView.Adapter<TrackSearchViewHolder>() {

    enum class ViewState {
        EMPTY,
        ERROR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        return when (viewState) {
            ViewState.EMPTY -> {
                val itemView = itemView(R.layout.track_empty_view, parent)
                TrackSearchEmptyViewHolder(itemView)
            }
            ViewState.ERROR -> {
                val itemView = itemView(R.layout.track_error_view, parent)
                TrackSearchErrorViewHolder(itemView, onRefreshClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
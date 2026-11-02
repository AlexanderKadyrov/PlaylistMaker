package com.practicum.playlistmaker.features.track.search.presentation.adapters

import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackFooterViewHolder
import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackHeaderViewHolder
import com.practicum.playlistmaker.features.track.search.presentation.adapters.holders.TrackViewHolder
import com.practicum.playlistmaker.core.extensions.itemView
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup

class TrackSectionAdapter(
    private val viewState: ViewState,
    private val onClick: (() -> Unit)? = null
): RecyclerView.Adapter<TrackViewHolder>() {

    enum class ViewState {
        HEADER,
        FOOTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return when (viewState) {
            ViewState.HEADER -> {
                val itemView = itemView(R.layout.track_header_view, parent)
                TrackHeaderViewHolder(itemView)
            }
            ViewState.FOOTER -> {
                val itemView = itemView(R.layout.track_footer_view, parent)
                TrackFooterViewHolder(itemView, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
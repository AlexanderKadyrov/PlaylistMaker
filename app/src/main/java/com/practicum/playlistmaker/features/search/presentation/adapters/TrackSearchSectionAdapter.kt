package com.practicum.playlistmaker.features.search.presentation.adapters

import com.practicum.playlistmaker.core.extensions.itemView
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.ViewGroup
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchFooterViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchHeaderViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchSectionAdapter(
    private val viewState: ViewState,
    private val onClick: (() -> Unit)? = null
): RecyclerView.Adapter<TrackSearchViewHolder>() {

    enum class ViewState {
        HEADER,
        FOOTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        return when (viewState) {
            ViewState.HEADER -> {
                val itemView = itemView(R.layout.track_header_view, parent)
                TrackSearchHeaderViewHolder(itemView)
            }
            ViewState.FOOTER -> {
                val itemView = itemView(R.layout.track_footer_view, parent)
                TrackSearchFooterViewHolder(itemView, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
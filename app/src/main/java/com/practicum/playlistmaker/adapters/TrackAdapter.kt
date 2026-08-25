package com.practicum.playlistmaker.adapters

import com.practicum.playlistmaker.views.TrackEmptyViewHolder
import com.practicum.playlistmaker.views.TrackItemViewHolder
import com.practicum.playlistmaker.views.TrackViewHolder
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import androidx.annotation.LayoutRes

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View

class TrackAdapter(): RecyclerView.Adapter<TrackViewHolder>() {

    private var state: TrackAdapterState = TrackAdapterState.BLANK

    override fun getItemViewType(position: Int): Int {
        return state.getItemViewType().value
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        return when (viewType) {
            TrackAdapterItemViewType.EMPTY.value -> {
                val itemView = itemView(R.layout.track_empty_view, parent)
                TrackEmptyViewHolder(itemView)
            }
            else -> {
                val itemView = itemView(R.layout.track_item_view, parent)
                TrackItemViewHolder(itemView)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        holder.bind(state.getTrack(position))
    }

    override fun getItemCount(): Int {
        return state.getTrackListSize()
    }

    fun set(state: TrackAdapterState) {
        this.state = state
        notifyDataSetChanged()
    }

    private fun itemView(
        @LayoutRes resource: Int,
        parent: ViewGroup
    ): View {
        return LayoutInflater
            .from(parent.context)
            .inflate(resource, parent, false)
    }
}
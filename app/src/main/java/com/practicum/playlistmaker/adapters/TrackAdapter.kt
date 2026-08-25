package com.practicum.playlistmaker.adapters

import com.practicum.playlistmaker.views.TrackViewHolder
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup

class TrackAdapter(): RecyclerView.Adapter<TrackViewHolder>() {

    private var state: TrackAdapterState = TrackAdapterState.BLANK

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.track_view, parent, false)
        return TrackViewHolder(view)
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
        when (state) {
            TrackAdapterState.SUCCESS -> {
                holder.bind(state.trackList[position])
            }
            else -> {}
        }
    }

    override fun getItemCount(): Int {
        return when (state) {
            TrackAdapterState.SUCCESS -> {
                state.trackList.size
            }
            TrackAdapterState.BLANK -> {
                0
            }
            else -> {
                1
            }
        }
    }

    fun set(state: TrackAdapterState) {
        this.state = state
        notifyDataSetChanged()
    }
}
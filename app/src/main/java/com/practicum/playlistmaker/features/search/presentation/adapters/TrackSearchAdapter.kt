package com.practicum.playlistmaker.features.search.presentation.adapters

import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchItemViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchAdapter(
    private val onItemClick: (Track) -> Unit
): RecyclerView.Adapter<TrackSearchViewHolder>() {

    private var trackList: ArrayList<Track> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.track_item_view, parent, false)
        return TrackSearchItemViewHolder(itemView) { position ->
            onItemClick(trackList[position])
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    fun set(trackList: ArrayList<Track>) {
        this.trackList = trackList
        notifyDataSetChanged()
    }
}
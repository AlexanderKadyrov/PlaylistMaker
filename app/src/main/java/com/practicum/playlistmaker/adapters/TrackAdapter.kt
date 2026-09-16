package com.practicum.playlistmaker.adapters

import com.practicum.playlistmaker.views.TrackItemViewHolder
import com.practicum.playlistmaker.views.TrackViewHolder
import com.practicum.playlistmaker.models.Track
import com.practicum.playlistmaker.R

import androidx.recyclerview.widget.RecyclerView

import android.view.LayoutInflater
import android.view.ViewGroup

class TrackAdapter(
    private val onItemClick: (Track) -> Unit
): RecyclerView.Adapter<TrackViewHolder>() {

    private var trackList: ArrayList<Track> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackViewHolder {
        val itemView = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.track_item_view, parent, false)
        return TrackItemViewHolder(itemView) { position ->
            onItemClick(trackList[position])
        }
    }

    override fun onBindViewHolder(holder: TrackViewHolder, position: Int) {
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
package com.practicum.playlistmaker.features.search.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.inflateBinding
import com.practicum.playlistmaker.databinding.TrackItemViewBinding
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchItemViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchAdapter(
    private val onItemClick: (Track) -> Unit
) : RecyclerView.Adapter<TrackSearchViewHolder>() {

    private var trackList: List<Track> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        val binding = parent.inflateBinding(TrackItemViewBinding::inflate)
        return TrackSearchItemViewHolder(binding) { position ->
            onItemClick(trackList[position])
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {
        holder.bind(trackList[position])
    }

    override fun getItemCount(): Int {
        return trackList.size
    }

    fun set(trackList: List<Track>) {
        this.trackList = trackList
        notifyDataSetChanged()
    }
}
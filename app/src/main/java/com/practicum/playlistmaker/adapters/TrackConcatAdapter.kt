package com.practicum.playlistmaker.adapters

import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.models.Track

class TrackConcatAdapter(
    private val onItemClick: (Track) -> Unit,
    private val onRefreshClick: () -> Unit
) {
    private val baseConcatAdapter = BaseConcatAdapter()

    fun showStoredTrackList(trackList: ArrayList<Track>) {
        showTrackList(trackList)
    }

    fun showTrackList(trackList: ArrayList<Track>) {
        val trackAdapter = TrackAdapter(
            onItemClick = { track ->
                onItemClick(track)
            })
        trackAdapter.set(trackList)
        baseConcatAdapter.clear()
        baseConcatAdapter.add(trackAdapter)
    }

    fun connect(recyclerView: RecyclerView) {
        baseConcatAdapter.connect(recyclerView)
    }

    fun clear() {
        baseConcatAdapter.clear()
    }
}
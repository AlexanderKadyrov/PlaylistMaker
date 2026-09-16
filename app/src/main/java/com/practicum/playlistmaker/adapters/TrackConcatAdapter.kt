package com.practicum.playlistmaker.adapters

import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.models.Track

class TrackConcatAdapter(
    private val onItemClick: (Track) -> Unit
) {
    private val baseConcatAdapter = BaseConcatAdapter()

    fun showStoredTrackList(
        trackList: ArrayList<Track>,
        onClearHistoryClick: () -> Unit
    ) {
        if (trackList.isEmpty()) {
            return
        }
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackSectionAdapter(
                viewState = TrackSectionAdapter.ViewState.HEADER
            )
        )
        baseConcatAdapter.add(trackAdapter(trackList))
        baseConcatAdapter.add(
            TrackSectionAdapter(
                viewState = TrackSectionAdapter.ViewState.FOOTER,
                onClick = {
                    baseConcatAdapter.clear()
                    onClearHistoryClick()
                }
            )
        )
    }

    fun showTrackList(trackList: ArrayList<Track>) {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(trackAdapter(trackList))
    }

    fun showTrackListErrorPlaceholder(onRefreshClick: () -> Unit) {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackPlaceholderAdapter(
                viewState = TrackPlaceholderAdapter.ViewState.ERROR,
                onRefreshClick = onRefreshClick
            )
        )
    }

    fun showTrackListEmptyPlaceholder() {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackPlaceholderAdapter(
                viewState = TrackPlaceholderAdapter.ViewState.EMPTY
            )
        )
    }

    fun connect(recyclerView: RecyclerView) {
        baseConcatAdapter.connect(recyclerView)
    }

    fun clear() {
        baseConcatAdapter.clear()
    }

    private fun trackAdapter(
        trackList: ArrayList<Track>
    ): TrackAdapter {
        val trackAdapter = TrackAdapter(
            onItemClick = { track ->
                onItemClick(track)
            })
        trackAdapter.set(trackList)
        return trackAdapter
    }
}
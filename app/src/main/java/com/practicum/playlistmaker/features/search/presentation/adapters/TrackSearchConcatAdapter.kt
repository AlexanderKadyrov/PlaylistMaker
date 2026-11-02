package com.practicum.playlistmaker.features.search.presentation.adapters

import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.ui.adapters.BaseConcatAdapter

class TrackSearchConcatAdapter(
    private val onItemClick: (Track) -> Unit
) {
    private val baseConcatAdapter = BaseConcatAdapter()

    fun showStoredTrackList(
        trackList: List<Track>,
        onClearHistoryClick: () -> Unit
    ) {
        if (trackList.isEmpty()) {
            return
        }
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackSearchSectionAdapter(
                viewState = TrackSearchSectionAdapter.ViewState.HEADER
            )
        )
        baseConcatAdapter.add(trackAdapter(trackList))
        baseConcatAdapter.add(
            TrackSearchSectionAdapter(
                viewState = TrackSearchSectionAdapter.ViewState.FOOTER,
                onClick = {
                    baseConcatAdapter.clear()
                    onClearHistoryClick()
                }
            )
        )
    }

    fun showTrackList(trackList: List<Track>) {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(trackAdapter(trackList))
    }

    fun showTrackListErrorPlaceholder(onRefreshClick: () -> Unit) {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackSearchPlaceholderAdapter(
                viewState = TrackSearchPlaceholderAdapter.ViewState.ERROR,
                onRefreshClick = onRefreshClick
            )
        )
    }

    fun showTrackListEmptyPlaceholder() {
        baseConcatAdapter.clear()
        baseConcatAdapter.add(
            TrackSearchPlaceholderAdapter(
                viewState = TrackSearchPlaceholderAdapter.ViewState.EMPTY
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
        trackList: List<Track>
    ): TrackSearchAdapter {
        val trackSearchAdapter = TrackSearchAdapter(
            onItemClick = { track ->
                onItemClick(track)
            })
        trackSearchAdapter.set(trackList)
        return trackSearchAdapter
    }
}
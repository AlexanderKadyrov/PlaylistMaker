package com.practicum.playlistmaker.features.search.presentation.vm

import com.practicum.playlistmaker.core.domain.model.Track

sealed interface TrackSearchState {
    data object Loading: TrackSearchState
    data class Content(val tracks: List<Track>) : TrackSearchState
    data class History(val tracks: List<Track>) : TrackSearchState
    data object Empty: TrackSearchState
    data object Error : TrackSearchState
}
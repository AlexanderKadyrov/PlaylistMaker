package com.practicum.playlistmaker.features.search.domain.repository

import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track

interface TrackSearchRepository {
    fun fetchTrackList(text: String, completion: (Result<List<Track>>) -> Unit)
    fun fetchStoredTrackList(): ArrayList<Track>
    fun store(track: Track)
    fun clearStoredTrackList()
}
package com.practicum.playlistmaker.adapters

import com.practicum.playlistmaker.models.Track

enum class TrackAdapterState(
    var trackList: ArrayList<Track> = arrayListOf()
) {
    SUCCESS(),
    EMPTY(),
    BLANK(),
    ERROR()
}
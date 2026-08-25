package com.practicum.playlistmaker.adapters

import com.practicum.playlistmaker.models.Track

enum class TrackAdapterState(
    private var trackList: ArrayList<Track> = arrayListOf()
) {
    SUCCESS(),
    BLANK(),
    EMPTY(),
    ERROR();

    fun set(trackList: ArrayList<Track>) {
        this.trackList = trackList
    }

    fun getItemViewType(): TrackAdapterItemViewType {
        return when (this) {
            EMPTY -> TrackAdapterItemViewType.EMPTY
            ERROR -> TrackAdapterItemViewType.ERROR
            else -> TrackAdapterItemViewType.ITEM
        }
    }

    fun getTrackListSize(): Int {
        return when (this) {
            EMPTY -> {
                1
            }
            ERROR -> {
                1
            }
            else -> {
                trackList.size
            }
        }
    }

    fun getTrack(position: Int): Track? {
        if (trackList.isEmpty()) {
            return null
        }
        return trackList[position]
    }
}
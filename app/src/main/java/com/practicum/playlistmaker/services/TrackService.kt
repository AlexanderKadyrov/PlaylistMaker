package com.practicum.playlistmaker.services

import com.practicum.playlistmaker.models.TrackResponse

import retrofit2.Response

interface TrackService {
    fun fetchTrackList(): Response<TrackResponse>
}
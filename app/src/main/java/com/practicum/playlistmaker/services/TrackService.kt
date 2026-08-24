package com.practicum.playlistmaker.services

import com.practicum.playlistmaker.models.TrackResponse

import retrofit2.Call

interface TrackService {
    fun fetchTrackList(): Call<TrackResponse>
}
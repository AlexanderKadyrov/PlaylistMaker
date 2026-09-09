package com.practicum.playlistmaker.services

import com.practicum.playlistmaker.models.TrackResponse

import retrofit2.http.Query
import retrofit2.http.GET
import retrofit2.Call

interface TrackService {
    @GET("search?entity=song")
    fun fetchTrackList(@Query("term") text: String): Call<TrackResponse>
}
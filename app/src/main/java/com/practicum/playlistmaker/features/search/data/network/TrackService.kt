package com.practicum.playlistmaker.features.search.data.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface TrackService {
    @GET("search?entity=song")
    fun fetchTrackList(@Query("term") text: String): Call<TrackResponse>
}
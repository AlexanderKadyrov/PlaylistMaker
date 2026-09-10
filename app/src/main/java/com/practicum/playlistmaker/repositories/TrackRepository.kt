package com.practicum.playlistmaker.repositories

import com.practicum.playlistmaker.clients.HTTPClientResult
import com.practicum.playlistmaker.services.TrackService
import com.practicum.playlistmaker.models.TrackResponse
import com.practicum.playlistmaker.clients.HTTPClient

object TrackRepository {

    private val client = HTTPClient<TrackService>("https://itunes.apple.com/")

    fun fetchTrackList(text: String, completion: (HTTPClientResult<TrackResponse>) -> Unit) {
        client.fetch({ it.fetchTrackList(text) }, completion)
    }
}
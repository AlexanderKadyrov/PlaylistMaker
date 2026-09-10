package com.practicum.playlistmaker.repositories

import com.practicum.playlistmaker.clients.HTTPClientResult
import com.practicum.playlistmaker.services.TrackService
import com.practicum.playlistmaker.models.TrackResponse
import com.practicum.playlistmaker.clients.HTTPClient

import android.content.SharedPreferences
import android.content.Context

object TrackRepository {

    private const val SHARED_PREFERENCES_TRACK_REPOSITORY = "shared_preferences_track_repository"
    private const val SHARED_PREFERENCES_TRACK_KEY = "shared_preferences_track_key"

    private lateinit var sharedPreferences: SharedPreferences

    private val client = HTTPClient<TrackService>("https://itunes.apple.com/")

    fun init(context: Context) {
        sharedPreferences = context
            .applicationContext
            .getSharedPreferences(SHARED_PREFERENCES_TRACK_REPOSITORY, Context.MODE_PRIVATE)
    }

    fun fetchTrackList(text: String, completion: (HTTPClientResult<TrackResponse>) -> Unit) {
        client.fetch({ it.fetchTrackList(text) }, completion)
    }
}
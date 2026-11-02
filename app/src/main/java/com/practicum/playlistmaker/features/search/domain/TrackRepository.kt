package com.practicum.playlistmaker.features.search.domain

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicum.playlistmaker.core.data.client.HTTPClient
import com.practicum.playlistmaker.core.data.client.HTTPClientResult
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.data.network.TrackResponse
import com.practicum.playlistmaker.features.search.data.network.TrackService

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

    fun fetchStoredTrackList(): ArrayList<Track> {
        val json = sharedPreferences.getString(SHARED_PREFERENCES_TRACK_KEY, null)
        if (json.isNullOrEmpty()) {
            return arrayListOf()
        }
        val type = object : TypeToken<ArrayList<Track>>() {}.type
        return Gson().fromJson(json, type) ?: arrayListOf()
    }

    fun clearStoredTrackList() {
        sharedPreferences
            .edit {
                clear()
            }
    }

    fun store(track: Track) {
        val trackList = fetchStoredTrackList()
        trackList.removeAll { it.trackId == track.trackId }
        trackList.add(0, track)
        val tenTrackList = trackList.take(10)
        val json = Gson().toJson(tenTrackList)
        sharedPreferences
            .edit {
                putString(SHARED_PREFERENCES_TRACK_KEY, json)
            }
    }
}
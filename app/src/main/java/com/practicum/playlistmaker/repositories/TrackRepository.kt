package com.practicum.playlistmaker.repositories

import com.practicum.playlistmaker.services.TrackService
import com.practicum.playlistmaker.models.TrackResponse
import com.practicum.playlistmaker.clients.HTTPClient
import com.practicum.playlistmaker.models.Track

import com.google.gson.reflect.TypeToken
import com.google.gson.Gson

import com.practicum.playlistmaker.R

import android.content.res.Resources
import com.practicum.playlistmaker.clients.HTTPClientResult
import java.io.InputStreamReader
import java.io.BufferedReader

class TrackRepository {

    private val client = HTTPClient<TrackService>("https://itunes.apple.com/")
    companion object {
        fun getTrackList(resources: Resources): List<Track> {
            val inputStream = resources.openRawResource(R.raw.track_list_mock)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val itemType = object : TypeToken<List<Track>>() {}.type
            return Gson().fromJson(reader, itemType)
        }
    }

    suspend fun fetchTrackList(): HTTPClientResult<TrackResponse> {
        return client.fetch { service ->
            service.fetchTrackList()
        }
    }
}
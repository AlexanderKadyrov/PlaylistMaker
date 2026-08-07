package com.practicum.playlistmaker.repositories

import com.practicum.playlistmaker.models.Track
import com.google.gson.reflect.TypeToken
import android.content.res.Resources
import com.practicum.playlistmaker.R
import java.io.InputStreamReader
import java.io.BufferedReader
import com.google.gson.Gson

class TrackRepository {
    companion object {
        fun getTrackList(resources: Resources): List<Track> {
            val inputStream = resources.openRawResource(R.raw.track_list_mock)
            val reader = BufferedReader(InputStreamReader(inputStream))
            val itemType = object : TypeToken<List<Track>>() {}.type
            return Gson().fromJson(reader, itemType)
        }
    }
}
package com.practicum.playlistmaker.features.search.data.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.practicum.playlistmaker.core.data.client.HTTPClient
import com.practicum.playlistmaker.core.data.client.HTTPClientResult
import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.domain.storage.LocalStorage
import com.practicum.playlistmaker.features.search.data.mapper.TrackSearchMapper
import com.practicum.playlistmaker.features.search.data.network.TrackSearchService
import com.practicum.playlistmaker.features.search.domain.repository.TrackSearchRepository

class TrackSearchRepositoryImpl(
    private val localStorage: LocalStorage,
    private val client: HTTPClient<TrackSearchService>,
    private val mapper: TrackSearchMapper
) : TrackSearchRepository {

    override fun fetchTrackList(text: String, completion: (Result<List<Track>>) -> Unit) {
        client
            .fetch(
                serviceCallback = {
                    it.fetchTrackList(text)
                },
                completionCallback = { result ->
                    when (result) {
                        is HTTPClientResult.SuccessData -> {
                            completion(Result.Success(mapper.map(result.data)))
                        }

                        is HTTPClientResult.SuccessNothing -> {
                            completion(Result.Success(listOf()))
                        }

                        is HTTPClientResult.Error -> {
                            completion(Result.Error(result.message))
                        }
                    }
                })
    }

    override fun fetchStoredTrackList(): ArrayList<Track> {
        val json = localStorage.getString(SHARED_PREFERENCES_TRACK_KEY)
        if (json.isNullOrEmpty()) {
            return arrayListOf()
        }
        val type = object : TypeToken<ArrayList<Track>>() {}.type
        return Gson().fromJson(json, type) ?: arrayListOf()
    }

    override fun store(track: Track) {
        val trackList = fetchStoredTrackList()
        trackList.removeAll { it.trackId == track.trackId }
        trackList.add(0, track)
        val tenTrackList = trackList.take(10)
        val json = Gson().toJson(tenTrackList)
        localStorage.putString(SHARED_PREFERENCES_TRACK_KEY, json)
    }

    override fun clearStoredTrackList() {
        localStorage.remove(SHARED_PREFERENCES_TRACK_KEY)
    }

    private companion object {
        const val SHARED_PREFERENCES_TRACK_KEY = "shared_preferences_track_key"
    }
}
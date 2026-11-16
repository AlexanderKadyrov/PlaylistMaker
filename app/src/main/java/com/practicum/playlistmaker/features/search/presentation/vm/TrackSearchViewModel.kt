package com.practicum.playlistmaker.features.search.presentation.vm

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor

class TrackSearchViewModel(
    private val trackSearchInteractor: TrackSearchInteractor
) : ViewModel() {

    private val handler = Handler(Looper.getMainLooper())

    private val _state = MutableLiveData<TrackSearchState>()
    val state: LiveData<TrackSearchState> = _state

    private var latestSearchText: String? = null

    fun clearTrackList() {
        latestSearchText = null
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
        _state.postValue(TrackSearchState.Content(emptyList()))
    }

    fun fetchHistoryTrackList() {
        val trackList = trackSearchInteractor.fetchStoredTrackList()
        _state.postValue(TrackSearchState.History(trackList))
    }

    fun fetchDelayedTrackList(
        text: String
    ) {
        if (latestSearchText == text) {
            return
        }
        latestSearchText = text
        _state.postValue(TrackSearchState.Loading)
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
        handler.postDelayed(
            { fetchImmediatelyTrackList(text) },
            SEARCH_REQUEST_TOKEN,
            SEARCH_DEBOUNCE_DELAY
        )
    }

    fun fetchImmediatelyTrackList(
        text: String
    ) {
        latestSearchText = text
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
        _state.postValue(TrackSearchState.Loading)
        trackSearchInteractor.fetchTrackList(
            text = text,
            consumer = object : TrackSearchInteractor.TrackSearchConsumer {
                override fun consume(result: Result<List<Track>>) {
                    if (latestSearchText.isNullOrEmpty()) {
                        return
                    }
                    when (result) {
                        is Result.Success -> {
                            if (result.data.isEmpty()) {
                                _state.postValue(TrackSearchState.Empty)
                            } else {
                                _state.postValue(TrackSearchState.Content(result.data))
                            }
                        }

                        is Result.Error -> {
                            _state.postValue(TrackSearchState.Error)
                        }
                    }
                }
            })
    }

    fun clearStoredTrackList() {
        trackSearchInteractor.clearStoredTrackList()
        _state.postValue(TrackSearchState.Content(emptyList()))
    }

    fun store(track: Track) {
        trackSearchInteractor.store(track)
    }

    override fun onCleared() {
        super.onCleared()
        handler.removeCallbacksAndMessages(SEARCH_REQUEST_TOKEN)
    }

    companion object Companion {
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private val SEARCH_REQUEST_TOKEN = Any()
    }
}
package com.practicum.playlistmaker.features.search.presentation

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.App
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.IntentExtra
import com.practicum.playlistmaker.core.domain.model.Result
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.hideKeyboard
import com.practicum.playlistmaker.core.extensions.startActivity
import com.practicum.playlistmaker.features.player.presentation.TrackPlayerActivity
import com.practicum.playlistmaker.features.search.di.trackSearchInteractor
import com.practicum.playlistmaker.features.search.domain.interactor.TrackSearchInteractor
import com.practicum.playlistmaker.features.search.presentation.adapters.TrackSearchConcatAdapter

class TrackSearchActivity : AppCompatActivity() {

    private val trackSearchInteractor by lazy {
        (applicationContext as App).creator.trackSearchInteractor()
    }

    private val searchRunnable = Runnable { fetchTrackList() }
    private val handler = Handler(Looper.getMainLooper())

    private var isAllowedClick = true

    private val trackSearchConcatAdapter = TrackSearchConcatAdapter(
        onItemClick = { track ->
            if (clickDebounce()) {
                startTrackActivity(track)
            }
        }
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureToolbar()
        configureUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_INSTANCE_STATE_KEY, searchEditText().text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(SEARCH_INSTANCE_STATE_KEY).let { text ->
            searchEditText().setText(text)
        }
    }

    private fun configureUI() {
        val clearImageView = findViewById<ImageView>(R.id.clearImageView)
        val searchEditText = searchEditText()
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && searchEditText.text.isEmpty()) {
                showStoredTracks()
            }
        }
        searchEditText.doOnTextChanged { charSequence, _, _, _ ->
            val text = charSequence.toString()
            val visibility = visibility(text)
            clearImageView.visibility = visibility
            if (visibility == View.GONE) {
                trackSearchConcatAdapter.clear()
            } else {
                fetchDebounceTrackList()
            }
        }
        clearImageView.setOnClickListener {
            trackSearchConcatAdapter.clear()
            searchEditText.text.clear()
            searchEditText.hideKeyboard()
        }
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        trackSearchConcatAdapter.connect(recyclerView)
    }

    private fun searchEditText(): EditText {
        return findViewById<EditText>(R.id.searchEditText)
    }

    private fun visibility(text: String): Int {
        return if (text.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    private fun fetchDebounceTrackList() {
        handler.removeCallbacks(searchRunnable)
        handler.postDelayed(searchRunnable, SEARCH_DEBOUNCE_DELAY)
    }

    private fun fetchTrackList() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)
        recyclerView.visibility = View.GONE
        progressBar.visibility = View.VISIBLE

        val text = searchEditText().text.toString()
        trackSearchInteractor.fetchTrackList(
            text = text,
            consumer = object : TrackSearchInteractor.TrackSearchConsumer {
                override fun consume(result: Result<List<Track>>) {
                    when (result) {
                        is Result.Success -> {
                            if (result.data.isEmpty()) {
                                trackSearchConcatAdapter.showTrackListEmptyPlaceholder()
                            } else {
                                trackSearchConcatAdapter.showTrackList(result.data)
                            }
                        }

                        is Result.Error -> {
                            trackSearchConcatAdapter.showTrackListErrorPlaceholder {
                                fetchTrackList()
                            }
                        }
                    }
                    recyclerView.visibility = View.VISIBLE
                    progressBar.visibility = View.GONE
                }
            })
    }

    private fun showStoredTracks() {
        val trackList = trackSearchInteractor.fetchStoredTrackList()
        trackSearchConcatAdapter.showStoredTrackList(
            trackList = trackList,
            onClearHistoryClick = {
                trackSearchInteractor.clearStoredTrackList()
            }
        )
    }

    private fun startTrackActivity(track: Track) {
        trackSearchInteractor.store(track)
        startActivity<TrackPlayerActivity> {
            val intentExtra = IntentExtra()
            intentExtra.putModel(Track.Companion.TRACK_INTENT_EXTRA, track)
            return@startActivity intentExtra
        }
    }

    private fun clickDebounce(): Boolean {
        val current = isAllowedClick
        if (isAllowedClick) {
            isAllowedClick = false
            handler.postDelayed({ isAllowedClick = true }, CLICK_DEBOUNCE_DELAY)
        }
        return current
    }

    companion object Companion {
        const val SEARCH_INSTANCE_STATE_KEY = "SEARCH_INSTANCE_STATE_KEY"
        private const val SEARCH_DEBOUNCE_DELAY = 2000L
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
}
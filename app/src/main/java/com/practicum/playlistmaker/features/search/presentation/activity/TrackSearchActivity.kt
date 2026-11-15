package com.practicum.playlistmaker.features.search.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.IntentExtra
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.hideKeyboard
import com.practicum.playlistmaker.core.extensions.startActivity
import com.practicum.playlistmaker.core.extensions.viewModelFactory
import com.practicum.playlistmaker.di.creator
import com.practicum.playlistmaker.features.player.presentation.TrackPlayerActivity
import com.practicum.playlistmaker.features.search.di.trackSearchViewModel
import com.practicum.playlistmaker.features.search.presentation.adapters.TrackSearchConcatAdapter
import com.practicum.playlistmaker.features.search.presentation.vm.TrackSearchState

class TrackSearchActivity : AppCompatActivity() {

    private val viewModel by viewModelFactory { creator.trackSearchViewModel() }

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

        observeViewModel()
    }

    private fun configureUI() {
        val clearImageView = findViewById<ImageView>(R.id.clearImageView)
        val searchEditText = searchEditText()
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && searchEditText.text.isEmpty()) {
                viewModel.fetchHistoryTrackList()
            }
        }
        searchEditText.doOnTextChanged { charSequence, _, _, _ ->
            val text = charSequence.toString()
            val visibility = visibility(text)
            clearImageView.visibility = visibility
            if (visibility == View.GONE) {
                viewModel.clearTrackList()
            } else {
                viewModel.fetchDelayedTrackList(text)
            }
        }
        clearImageView.setOnClickListener {
            viewModel.clearTrackList()
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

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            render(state)
        }
    }

    private fun render(state: TrackSearchState) {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val progressBar = findViewById<ProgressBar>(R.id.progressBar)

        recyclerView.isVisible = state !is TrackSearchState.Loading
        progressBar.isVisible = state is TrackSearchState.Loading

        when (state) {
            is TrackSearchState.Loading -> Unit

            is TrackSearchState.Content -> {
                trackSearchConcatAdapter.showTrackList(state.tracks)
            }

            is TrackSearchState.History -> {
                trackSearchConcatAdapter.showStoredTrackList(
                    trackList = state.tracks,
                    onClearHistoryClick = {
                        viewModel.clearStoredTrackList()
                    }
                )
            }

            is TrackSearchState.Empty -> {
                trackSearchConcatAdapter.showTrackListEmptyPlaceholder()
            }

            is TrackSearchState.Error -> {
                val text = searchEditText().text.toString()
                trackSearchConcatAdapter.showTrackListErrorPlaceholder {
                    viewModel.fetchImmediatelyTrackList(text)
                }
            }
        }
    }

    private fun startTrackActivity(track: Track) {
        viewModel.store(track)
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
        private const val CLICK_DEBOUNCE_DELAY = 1000L
    }
}
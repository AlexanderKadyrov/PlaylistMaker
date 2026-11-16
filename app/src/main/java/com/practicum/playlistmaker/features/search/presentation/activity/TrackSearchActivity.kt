package com.practicum.playlistmaker.features.search.presentation.activity

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.hideKeyboard
import com.practicum.playlistmaker.core.extensions.startActivity
import com.practicum.playlistmaker.databinding.ActivitySearchBinding
import com.practicum.playlistmaker.features.player.presentation.activity.TrackPlayerActivity
import com.practicum.playlistmaker.features.search.presentation.adapters.TrackSearchConcatAdapter
import com.practicum.playlistmaker.features.search.presentation.vm.TrackSearchState
import com.practicum.playlistmaker.features.search.presentation.vm.TrackSearchViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TrackSearchActivity : AppCompatActivity() {

    private val viewModel by viewModel<TrackSearchViewModel>()

    private val binding by lazy { ActivitySearchBinding.inflate(layoutInflater) }

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
        setContentView(binding.root)

        configureToolbar()
        configureUI()
        observeViewModel()
    }

    private fun configureUI() = with(binding) {
        trackSearchConcatAdapter.connect(recyclerView)

        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && searchEditText.text.isEmpty()) {
                viewModel.fetchHistoryTrackList()
            }
        }

        searchEditText.doOnTextChanged { charSequence, _, _, _ ->
            val text = charSequence?.toString().orEmpty()
            clearImageView.isVisible = text.isNotEmpty()
            if (text.isNotEmpty()) {
                viewModel.fetchDelayedTrackList(text)
            } else {
                viewModel.clearTrackList()
            }
        }

        clearImageView.setOnClickListener {
            viewModel.clearTrackList()
            searchEditText.text.clear()
            searchEditText.hideKeyboard()
        }
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            render(state)
        }
    }

    private fun render(state: TrackSearchState) = with(binding) {
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
                val text = searchEditText.text.toString()
                trackSearchConcatAdapter.showTrackListErrorPlaceholder {
                    viewModel.fetchImmediatelyTrackList(text)
                }
            }
        }
    }

    private fun startTrackActivity(track: Track) {
        viewModel.store(track)
        startActivity<TrackPlayerActivity>(
            key = Track.TRACK_INTENT_EXTRA,
            data = track
        )
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
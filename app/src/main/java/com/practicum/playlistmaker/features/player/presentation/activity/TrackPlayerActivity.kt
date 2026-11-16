package com.practicum.playlistmaker.features.player.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.getModel
import com.practicum.playlistmaker.databinding.ActivityTrackBinding
import com.practicum.playlistmaker.features.player.presentation.vm.TrackPlayerState
import com.practicum.playlistmaker.features.player.presentation.vm.TrackPlayerViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class TrackPlayerActivity : AppCompatActivity() {

    private val viewModel by viewModel<TrackPlayerViewModel> {
        val intentExtra = intent.getModel()
        val track = intentExtra?.getModel<Track>(Track.TRACK_INTENT_EXTRA)
            ?: error("Track must be passed to TrackPlayerActivity")
        parametersOf(track)
    }

    private val binding by lazy { ActivityTrackBinding.inflate(layoutInflater) }

    override fun onPause() {
        super.onPause()
        viewModel.onPause()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        configureUI()
        observeViewModel()
    }

    private fun configureUI() = with(binding) {
        playPauseButton.setOnClickListener {
            viewModel.playbackControl()
        }
    }

    private fun observeViewModel() {
        viewModel.trackModelState.observe(this) { track ->
            render(track)
        }
        viewModel.trackPlayerState.observe(this) { state ->
            render(state)
        }
    }

    private fun render(track: Track) = with(binding) {
        trackNameTextView.text = track.trackName
        trackArtistNameTextView.text = track.artistName

        trackDurationValueTextView.text = track.convertTrackTimeString()

        val collectionName = track.collectionName.orEmpty()
        trackAlbumValueTextView.text = collectionName
        trackAlbumValueTextView.isVisible = collectionName.isNotEmpty()
        trackAlbumNameTextView.isVisible = collectionName.isNotEmpty()

        val trackYear = track.convertTrackYearString().orEmpty()
        trackYearValueTextView.text = trackYear
        trackYearValueTextView.isVisible = trackYear.isNotEmpty()
        trackYearNameTextView.isVisible = trackYear.isNotEmpty()

        trackGenreValueTextView.text = track.primaryGenreName
        trackCountryValueTextView.text = track.country

        Glide.with(this@TrackPlayerActivity)
            .load(track.artworkUrl())
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackCoverImageView)
    }

    private fun render(state: TrackPlayerState) = with(binding) {
        trackTimeValueTextView.text = state.currentPosition ?: getString(R.string.track_time_zero)
        playPauseButton.isEnabled = state.isPlayButtonEnabled
        playPauseButton.isChecked = state.isPlaying
    }
}
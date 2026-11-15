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
import com.practicum.playlistmaker.core.extensions.viewModelFactory
import com.practicum.playlistmaker.databinding.ActivityTrackBinding
import com.practicum.playlistmaker.di.creator
import com.practicum.playlistmaker.features.player.di.trackPlayerViewModel
import com.practicum.playlistmaker.features.player.presentation.vm.TrackPlayerState

class TrackPlayerActivity : AppCompatActivity() {

    private val viewModel by viewModelFactory {
        val intentExtra = intent.getModel()
        val track = intentExtra?.getModel<Track>(Track.TRACK_INTENT_EXTRA)
            ?: error("Track must be passed to TrackPlayerActivity")
        creator.trackPlayerViewModel(track)
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

        trackNameTextView.text = viewModel.track.trackName
        trackArtistNameTextView.text = viewModel.track.artistName

        trackDurationValueTextView.text = viewModel.track.convertTrackTimeString()

        val collectionName = viewModel.track.collectionName.orEmpty()
        trackAlbumValueTextView.text = collectionName
        trackAlbumValueTextView.isVisible = collectionName.isNotEmpty()
        trackAlbumNameTextView.isVisible = collectionName.isNotEmpty()

        val trackYear = viewModel.track.convertTrackYearString().orEmpty()
        trackYearValueTextView.text = trackYear
        trackYearValueTextView.isVisible = trackYear.isNotEmpty()
        trackYearNameTextView.isVisible = trackYear.isNotEmpty()

        trackGenreValueTextView.text = viewModel.track.primaryGenreName
        trackCountryValueTextView.text = viewModel.track.country

        Glide.with(this@TrackPlayerActivity)
            .load(viewModel.track.artworkUrl())
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackCoverImageView)
    }

    private fun observeViewModel() {
        viewModel.state.observe(this) { state ->
            render(state)
        }
    }

    private fun render(state: TrackPlayerState) = with(binding) {
        trackTimeValueTextView.text = state.currentPosition ?: getString(R.string.track_time_zero)
        playPauseButton.isEnabled = state.isPlayButtonEnabled
        playPauseButton.isChecked = state.isPlaying
    }
}
package com.practicum.playlistmaker.features.player.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.playlistmaker.App
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.getModel
import com.practicum.playlistmaker.databinding.ActivityTrackBinding
import com.practicum.playlistmaker.features.player.di.trackPlayerInteractor
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerActivity : AppCompatActivity() {

    private val trackPlayerInteractor by lazy {
        (applicationContext as App).creator.trackPlayerInteractor()
    }

    private val binding by lazy { ActivityTrackBinding.inflate(layoutInflater) }

    override fun onPause() {
        super.onPause()
        trackPlayerInteractor.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        trackPlayerInteractor.onDestroy()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        configureUI()
    }

    private fun configureUI(): Unit = with(binding) {
        val intentExtra = intent.getModel()
        val model = intentExtra?.getModel<Track>(Track.Companion.TRACK_INTENT_EXTRA) ?: return

        playPauseButton.setOnClickListener {
            trackPlayerInteractor.playbackControl()
        }

        trackNameTextView.text = model.trackName
        trackArtistNameTextView.text = model.artistName

        trackDurationValueTextView.text = model.convertTrackTimeString()

        val collectionName = model.collectionName.orEmpty()
        trackAlbumValueTextView.text = collectionName
        trackAlbumValueTextView.isVisible = collectionName.isNotEmpty()
        trackAlbumNameTextView.isVisible = collectionName.isNotEmpty()

        val trackYear = model.convertTrackYearString().orEmpty()
        trackYearValueTextView.text = trackYear
        trackYearValueTextView.isVisible = trackYear.isNotEmpty()
        trackYearNameTextView.isVisible = trackYear.isNotEmpty()

        trackGenreValueTextView.text = model.primaryGenreName
        trackCountryValueTextView.text = model.country

        Glide.with(this@TrackPlayerActivity)
            .load(model.artworkUrl())
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackCoverImageView)

        configurePlayer(model.previewUrl)
    }

    private fun configurePlayer(url: String) {
        trackPlayerInteractor.configure(
            url = url,
            consumer = object : TrackPlayerInteractor.TrackPlayerConsumer {
                override fun consumeChangeCurrentPosition(value: String) {
                    setTrackTime(value)
                }

                override fun consumePrepared() {
                    setPlaybackEnabled()
                }

                override fun consumePlaying() {
                    setIsPlaying(true)
                }

                override fun consumePaused() {
                    setIsPlaying(false)
                }

                override fun consumeEnd() {
                    setIsPlaying(false)
                    setTrackTime(getString(R.string.track_time_zero))
                }
            }
        )
    }

    private fun setPlaybackEnabled() {
        binding.playPauseButton.isEnabled = true
    }

    private fun setIsPlaying(value: Boolean) {
        binding.playPauseButton.isChecked = value
    }

    private fun setTrackTime(value: String) {
        binding.trackTimeValueTextView.text = value
    }
}
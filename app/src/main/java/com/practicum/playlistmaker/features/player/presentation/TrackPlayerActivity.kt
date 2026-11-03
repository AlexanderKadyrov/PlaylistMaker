package com.practicum.playlistmaker.features.player.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.practicum.playlistmaker.App
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.domain.model.Track
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.getModel
import com.practicum.playlistmaker.features.player.di.trackPlayerInteractor
import com.practicum.playlistmaker.features.player.domain.interactor.TrackPlayerInteractor

class TrackPlayerActivity : AppCompatActivity() {

    private val trackPlayerInteractor by lazy {
        (applicationContext as App).creator.trackPlayerInteractor()
    }

    @SuppressLint("StaticFieldLeak")
    private lateinit var trackTimeValueTextView: TextView
    private lateinit var playlistButton: ToggleButton
    private lateinit var favoriteButton: ToggleButton
    private lateinit var playPauseButton: ToggleButton

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
        setContentView(R.layout.activity_track)
        configureToolbar()
        configureUI()
    }

    private fun configureUI() {
        val intentExtra = intent.getModel()
        val track = intentExtra?.getModel<Track>(Track.Companion.TRACK_INTENT_EXTRA)
        track?.let { model ->
            configurePlayer(model.previewUrl)
            fill(model)
        }
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

    @SuppressLint("SetTextI18n")
    private fun fill(model: Track) {
        val trackCoverImageView = findViewById<ImageView>(R.id.trackCoverImageView)
        val trackNameTextView = findViewById<TextView>(R.id.trackNameTextView)
        val trackArtistNameTextView = findViewById<TextView>(R.id.trackArtistNameTextView)

        playlistButton = findViewById<ToggleButton>(R.id.playlistButton)
        favoriteButton = findViewById<ToggleButton>(R.id.favoriteButton)
        playPauseButton = findViewById<ToggleButton>(R.id.playPauseButton)
        playPauseButton.setOnClickListener {
            trackPlayerInteractor.playbackControl()
        }

        trackTimeValueTextView = findViewById<TextView>(R.id.trackTimeValueTextView)
        val trackDurationValueTextView = findViewById<TextView>(R.id.trackDurationValueTextView)

        val trackAlbumValueTextView = findViewById<TextView>(R.id.trackAlbumValueTextView)
        val trackAlbumNameTextView = findViewById<TextView>(R.id.trackAlbumNameTextView)

        val trackYearValueTextView = findViewById<TextView>(R.id.trackYearValueTextView)
        val trackYearNameTextView = findViewById<TextView>(R.id.trackYearNameTextView)

        val trackGenreValueTextView = findViewById<TextView>(R.id.trackGenreValueTextView)
        val trackCountryValueTextView = findViewById<TextView>(R.id.trackCountryValueTextView)

        trackNameTextView.text = model.trackName
        trackArtistNameTextView.text = model.artistName

        trackDurationValueTextView.text = model.convertTrackTimeString()

        val collectionName = model.collectionName ?: ""
        trackAlbumValueTextView.text = collectionName
        if (collectionName.isEmpty()) {
            trackAlbumValueTextView.visibility = View.GONE
            trackAlbumNameTextView.visibility = View.GONE
        }

        val trackYear = model.convertTrackYearString() ?: ""
        trackYearValueTextView.text = trackYear
        if (trackYear.isEmpty()) {
            trackYearValueTextView.visibility = View.GONE
            trackYearNameTextView.visibility = View.GONE
        }

        trackGenreValueTextView.text = model.primaryGenreName
        trackCountryValueTextView.text = model.country

        Glide.with(this)
            .load(model.artworkUrl())
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackCoverImageView)
    }

    private fun setPlaybackEnabled() {
        playPauseButton.isEnabled = true
    }

    private fun setIsPlaying(value: Boolean) {
        playPauseButton.isChecked = value
    }

    private fun setTrackTime(value: String) {
        trackTimeValueTextView.text = value
    }
}
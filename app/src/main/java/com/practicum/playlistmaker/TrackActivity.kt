package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar
import com.practicum.playlistmaker.extensions.getModel
import com.practicum.playlistmaker.models.Track

import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.Glide

import androidx.appcompat.app.AppCompatActivity

import android.annotation.SuppressLint
import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.os.Bundle

class TrackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        configureToolbar()
        configureUI()
    }

    private fun configureUI() {
        val intentExtra = intent.getModel()
        val track = intentExtra?.getModel<Track>(Track.TRACK_INTENT_EXTRA)
        track?.let { model ->
            fill(model)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun fill(model: Track) {
        val trackCoverImageView = findViewById<ImageView>(R.id.trackCoverImageView)
        val trackNameTextView = findViewById<TextView>(R.id.trackNameTextView)
        val trackArtistNameTextView = findViewById<TextView>(R.id.trackArtistNameTextView)
        val playlistButton = findViewById<Button>(R.id.playlistButton)
        val playPauseButton = findViewById<Button>(R.id.playPauseButton)
        val favoriteButton = findViewById<Button>(R.id.favoriteButton)
        val trackTimeValueTextView = findViewById<TextView>(R.id.trackTimeValueTextView)
        val trackDurationValueTextView = findViewById<TextView>(R.id.trackDurationValueTextView)
        val trackAlbumValueTextView = findViewById<TextView>(R.id.trackAlbumValueTextView)
        val trackYearValueTextView = findViewById<TextView>(R.id.trackYearValueTextView)
        val trackGenreValueTextView = findViewById<TextView>(R.id.trackGenreValueTextView)
        val trackCountryValueTextView = findViewById<TextView>(R.id.trackCountryValueTextView)

        trackNameTextView.text = model.trackName
        trackArtistNameTextView.text = model.artistName

        trackTimeValueTextView.text = "0:30"
        trackDurationValueTextView.text = model.convertTrackTimeString()
        trackAlbumValueTextView.text = model.collectionName
        trackYearValueTextView.text = model.convertTrackYearString()
        trackGenreValueTextView.text = model.primaryGenreName
        trackCountryValueTextView.text = model.country

        Glide.with(this)
            .load(model.artworkUrl100)
            .placeholder(R.drawable.ic_track_placeholder)
            .transform(RoundedCorners(10))
            .into(trackCoverImageView)
    }
}
package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar

import androidx.appcompat.app.AppCompatActivity

import android.widget.ImageView
import android.widget.TextView
import android.widget.Button
import android.os.Bundle

class TrackActivity : AppCompatActivity() {

    private val trackCoverImageView = findViewById<ImageView>(R.id.trackCoverImageView)
    private val trackNameTextView = findViewById<TextView>(R.id.trackNameTextView)
    private val trackArtistNameTextView = findViewById<TextView>(R.id.trackArtistNameTextView)
    private val playlistButton = findViewById<Button>(R.id.playlistButton)
    private val playPauseButton = findViewById<Button>(R.id.playPauseButton)
    private val favoriteButton = findViewById<Button>(R.id.favoriteButton)
    private val trackTimeValueTextView = findViewById<TextView>(R.id.trackTimeValueTextView)
    private val trackDurationValueTextView = findViewById<TextView>(R.id.trackDurationValueTextView)
    private val trackAlbumValueTextView = findViewById<TextView>(R.id.trackAlbumValueTextView)
    private val trackYearValueTextView = findViewById<TextView>(R.id.trackYearValueTextView)
    private val trackGenreValueTextView = findViewById<TextView>(R.id.trackGenreValueTextView)
    private val trackCountryValueTextView = findViewById<TextView>(R.id.trackCountryValueTextView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        configureToolbar()
    }
}
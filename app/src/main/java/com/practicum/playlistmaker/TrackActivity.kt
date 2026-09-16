package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar

import androidx.appcompat.app.AppCompatActivity

import android.widget.ImageView
import android.os.Bundle

class TrackActivity : AppCompatActivity() {

    private val trackCoverImageView = findViewById<ImageView>(R.id.trackCoverImageView)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        configureToolbar()
    }
}
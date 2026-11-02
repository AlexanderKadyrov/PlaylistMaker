package com.practicum.playlistmaker.features.main.presentation

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.features.search.presentation.TrackSearchActivity
import com.practicum.playlistmaker.features.settings.presentation.SettingsActivity
import com.practicum.playlistmaker.core.extensions.startActivity
import com.practicum.playlistmaker.features.media.presentation.MediaActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickSearch()
        setOnClickMedia()
        setOnClickSettings()
    }

    private fun setOnClickSearch() {
        val searchFrameLayout = findViewById<FrameLayout>(R.id.search)
        searchFrameLayout.setOnClickListener {
            startActivity<TrackSearchActivity>()
        }
    }

    private fun setOnClickMedia() {
        val mediaFrameLayout = findViewById<FrameLayout>(R.id.media)
        mediaFrameLayout.setOnClickListener {
            startActivity<MediaActivity>()
        }
    }

    private fun setOnClickSettings() {
        val settingsFrameLayout = findViewById<FrameLayout>(R.id.settings)
        settingsFrameLayout.setOnClickListener {
            startActivity<SettingsActivity>()
        }
    }
}
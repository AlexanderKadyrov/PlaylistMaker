package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.startActivity

import androidx.appcompat.app.AppCompatActivity

import android.widget.FrameLayout
import android.os.Bundle

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
            startActivity<SearchActivity>()
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
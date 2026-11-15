package com.practicum.playlistmaker.features.main.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practicum.playlistmaker.core.extensions.startActivity
import com.practicum.playlistmaker.databinding.ActivityMainBinding
import com.practicum.playlistmaker.features.media.presentation.MediaActivity
import com.practicum.playlistmaker.features.search.presentation.activity.TrackSearchActivity
import com.practicum.playlistmaker.features.settings.presentation.SettingsActivity

class MainActivity : AppCompatActivity() {

    private val binding by lazy { ActivityMainBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureUI()
    }

    private fun configureUI() = with(binding) {
        searchFrameLayout.setOnClickListener {
            startActivity<TrackSearchActivity>()
        }

        searchFrameLayout.setOnClickListener {
            startActivity<MediaActivity>()
        }

        searchFrameLayout.setOnClickListener {
            startActivity<SettingsActivity>()
        }
    }
}
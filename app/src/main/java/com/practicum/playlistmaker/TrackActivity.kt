package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar

import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle

class TrackActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_track)
        configureToolbar()
    }
}
package com.practicum.playlistmaker.extensions

import com.google.android.material.appbar.MaterialToolbar
import androidx.appcompat.app.AppCompatActivity
import com.practicum.playlistmaker.R

fun AppCompatActivity.configureToolbar() {
    val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
    toolbar.setOnClickListener {
        onBackPressedDispatcher.onBackPressed()
    }
}
package com.practicum.playlistmaker.extensions

import com.google.android.material.appbar.MaterialToolbar
import com.practicum.playlistmaker.R

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate

import android.content.Intent
import android.net.Uri

fun AppCompatActivity.configureToolbar() {
    val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
    toolbar.setOnClickListener {
        onBackPressedDispatcher.onBackPressed()
    }
}

fun AppCompatActivity.isSystemInDarkTheme(): Boolean {
    val currentMode = AppCompatDelegate.getDefaultNightMode()
    return currentMode == AppCompatDelegate.MODE_NIGHT_YES
}

fun AppCompatActivity.setDefaultNightMode(isChecked: Boolean) {
    val mode = if (isChecked) AppCompatDelegate.MODE_NIGHT_YES else AppCompatDelegate.MODE_NIGHT_NO
    AppCompatDelegate.setDefaultNightMode(mode)
}

fun AppCompatActivity.intentActionSend(text: String): Intent {
    val intent = Intent(Intent.ACTION_SEND)
    intent.putExtra(Intent.EXTRA_TEXT, text)
    intent.type = "text/plain"
    return intent
}

fun AppCompatActivity.intentActionSendTo(
    email: String,
    subject: String,
    text: String
): Intent {
    val intent = Intent(Intent.ACTION_SENDTO)
    intent.data = Uri.parse("mailto:")
    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(email))
    intent.putExtra(Intent.EXTRA_SUBJECT, subject)
    intent.putExtra(Intent.EXTRA_TEXT, text)
    return intent
}

fun AppCompatActivity.intentActionView(uri: String): Intent {
    return Intent(Intent.ACTION_VIEW, Uri.parse(uri))
}
package com.practicum.playlistmaker.core.extensions

import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.IntentCompat.getParcelableExtra
import com.google.android.material.appbar.MaterialToolbar
import com.practicum.playlistmaker.R

fun AppCompatActivity.configureToolbar() {
    val toolbar = findViewById<MaterialToolbar>(R.id.toolbar)
    toolbar.setOnClickListener {
        onBackPressedDispatcher.onBackPressed()
    }
}

inline fun <reified T : Parcelable> AppCompatActivity.getParcelableModel(
    key: String
): T? {
    return getParcelableExtra(intent, key, T::class.java)
}

inline fun <reified T : Any> AppCompatActivity.startActivity(
    key: String,
    data: Parcelable
) {
    val intent = Intent(this, T::class.java)
        .apply {
            putExtra(key, data)
        }
    startActivity(intent)
}

inline fun <reified T : Any> AppCompatActivity.startActivity() {
    val intent = Intent(this, T::class.java)
    startActivity(intent)
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
package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import androidx.core.widget.doOnTextChanged
import android.widget.ImageView
import android.widget.EditText
import android.view.View

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureToolbar()
        configureUI()
    }

    private fun configureUI() {
        val searchEditText = findViewById<EditText>(R.id.searchEditText)
        val clearImageView = findViewById<ImageView>(R.id.clearImageView)
        searchEditText.doOnTextChanged { text, start, before, count ->
            clearImageView.visibility = visibility(text)
        }
        clearImageView.setOnClickListener {
            searchEditText.text.clear()
        }
    }

    private fun visibility(s: CharSequence?): Int {
        return if (s.isNullOrEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }
}
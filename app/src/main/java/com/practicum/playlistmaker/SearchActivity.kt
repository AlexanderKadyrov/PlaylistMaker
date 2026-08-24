package com.practicum.playlistmaker

import com.practicum.playlistmaker.repositories.TrackRepository
import com.practicum.playlistmaker.extensions.configureToolbar
import com.practicum.playlistmaker.extensions.hideKeyboard
import com.practicum.playlistmaker.adapters.TrackAdapter

import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

import android.widget.ImageView
import android.widget.EditText
import android.view.View
import android.os.Bundle

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        configureToolbar()
        configureUI()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString(SEARCH_INSTANCE_STATE_KEY, searchEditText().text.toString())
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        savedInstanceState.getString(SEARCH_INSTANCE_STATE_KEY).let { text ->
            searchEditText().setText(text)
        }
    }

    private fun configureUI() {
        val clearImageView = findViewById<ImageView>(R.id.clearImageView)
        val searchEditText = searchEditText()
        searchEditText.doOnTextChanged { charSequence, _, _, _ ->
            val text = charSequence.toString()
            clearImageView.visibility = visibility(text)
        }
        clearImageView.setOnClickListener {
            searchEditText.text.clear()
            searchEditText.hideKeyboard()
        }
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = TrackAdapter(TrackRepository.getTrackList(resources))
    }

    private fun searchEditText(): EditText {
        return findViewById<EditText>(R.id.searchEditText)
    }

    private fun visibility(text: String): Int {
        return if (text.isEmpty()) {
            View.GONE
        } else {
            View.VISIBLE
        }
    }

    companion object {
        const val SEARCH_INSTANCE_STATE_KEY = "SEARCH_INSTANCE_STATE_KEY"
    }
}
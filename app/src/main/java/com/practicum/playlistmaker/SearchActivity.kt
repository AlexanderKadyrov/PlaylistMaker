package com.practicum.playlistmaker

import com.practicum.playlistmaker.repositories.TrackRepository
import com.practicum.playlistmaker.extensions.configureToolbar
import com.practicum.playlistmaker.adapters.TrackAdapterState
import com.practicum.playlistmaker.clients.HTTPClientResult
import com.practicum.playlistmaker.extensions.hideKeyboard
import com.practicum.playlistmaker.adapters.TrackAdapter

import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.EditText
import android.view.View
import android.os.Bundle

class SearchActivity : AppCompatActivity() {

    private val trackAdapter = TrackAdapter {
        fetchTrackList()
    }

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
            val visibility = visibility(text)
            clearImageView.visibility = visibility
            if (visibility == View.GONE) {
                trackAdapter.set(TrackAdapterState.BLANK)
            }
        }
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                fetchTrackList()
            }
            false
        }
        clearImageView.setOnClickListener {
            trackAdapter.set(TrackAdapterState.BLANK)
            searchEditText.text.clear()
            searchEditText.hideKeyboard()
        }
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.adapter = trackAdapter
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

    private fun fetchTrackList() {
        val text = searchEditText().text.toString()
        TrackRepository.fetchTrackList(text) { response ->
            when (response) {
                is HTTPClientResult.Success -> {
                    val trackList = response.data?.results ?: arrayListOf()
                    if (trackList.isEmpty()) {
                        val state = TrackAdapterState.EMPTY
                        trackAdapter.set(state)
                    } else {
                        val state = TrackAdapterState.SUCCESS
                        state.set(trackList)
                        trackAdapter.set(state)
                    }
                }
                is HTTPClientResult.Error -> {
                    val state = TrackAdapterState.ERROR
                    trackAdapter.set(state)
                }
            }
        }
    }

    companion object {
        const val SEARCH_INSTANCE_STATE_KEY = "SEARCH_INSTANCE_STATE_KEY"
    }
}
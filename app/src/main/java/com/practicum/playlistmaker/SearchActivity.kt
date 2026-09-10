package com.practicum.playlistmaker

import com.practicum.playlistmaker.repositories.TrackRepository
import com.practicum.playlistmaker.adapters.TrackConcatAdapter
import com.practicum.playlistmaker.extensions.configureToolbar
import com.practicum.playlistmaker.clients.HTTPClientResult
import com.practicum.playlistmaker.extensions.hideKeyboard

import androidx.recyclerview.widget.RecyclerView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged

import android.view.inputmethod.EditorInfo
import android.widget.ImageView
import android.widget.EditText
import android.view.View
import android.os.Bundle

class SearchActivity : AppCompatActivity() {

    private val trackConcatAdapter = TrackConcatAdapter(
        onItemClick = { track ->
            TrackRepository.store(track)
        }
    )

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
        searchEditText.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus && searchEditText.text.isEmpty()) {
                showStoredTracks()
            }
        }
        searchEditText.doOnTextChanged { charSequence, _, _, _ ->
            val text = charSequence.toString()
            val visibility = visibility(text)
            clearImageView.visibility = visibility
            if (visibility == View.GONE) {
                trackConcatAdapter.clear()
            }
        }
        searchEditText.setOnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                fetchTrackList()
            }
            false
        }
        clearImageView.setOnClickListener {
            trackConcatAdapter.clear()
            searchEditText.text.clear()
            searchEditText.hideKeyboard()
        }
        configureRecyclerView()
    }

    private fun configureRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        trackConcatAdapter.connect(recyclerView)
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
                        trackConcatAdapter.showTrackListEmptyPlaceholder()
                    } else {
                        trackConcatAdapter.showTrackList(trackList)
                    }
                }
                is HTTPClientResult.Error -> {
                    trackConcatAdapter.showTrackListErrorPlaceholder {
                        fetchTrackList()
                    }
                }
            }
        }
    }

    private fun showStoredTracks() {
        val trackList = TrackRepository.fetchStoredTrackList()
        trackConcatAdapter.showStoredTrackList(trackList)
    }

    companion object {
        const val SEARCH_INSTANCE_STATE_KEY = "SEARCH_INSTANCE_STATE_KEY"
    }
}
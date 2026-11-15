package com.practicum.playlistmaker.features.search.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.core.extensions.inflateBinding
import com.practicum.playlistmaker.databinding.TrackEmptyViewBinding
import com.practicum.playlistmaker.databinding.TrackErrorViewBinding
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchEmptyViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchErrorViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchPlaceholderAdapter(
    private val viewState: ViewState,
    private val onRefreshClick: (() -> Unit)? = null
) : RecyclerView.Adapter<TrackSearchViewHolder>() {

    enum class ViewState {
        EMPTY,
        ERROR
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        return when (viewState) {
            ViewState.EMPTY -> {
                val binding = parent.inflateBinding(TrackEmptyViewBinding::inflate)
                TrackSearchEmptyViewHolder(binding)
            }

            ViewState.ERROR -> {
                val binding = parent.inflateBinding(TrackErrorViewBinding::inflate)
                TrackSearchErrorViewHolder(binding, onRefreshClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
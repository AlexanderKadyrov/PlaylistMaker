package com.practicum.playlistmaker.features.search.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practicum.playlistmaker.core.extensions.inflateBinding
import com.practicum.playlistmaker.databinding.TrackFooterViewBinding
import com.practicum.playlistmaker.databinding.TrackHeaderViewBinding
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchFooterViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchHeaderViewHolder
import com.practicum.playlistmaker.features.search.presentation.adapters.holders.TrackSearchViewHolder

class TrackSearchSectionAdapter(
    private val viewState: ViewState,
    private val onClick: (() -> Unit)? = null
) : RecyclerView.Adapter<TrackSearchViewHolder>() {

    enum class ViewState {
        HEADER,
        FOOTER
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TrackSearchViewHolder {
        return when (viewState) {
            ViewState.HEADER -> {
                val binding = parent.inflateBinding(TrackHeaderViewBinding::inflate)
                TrackSearchHeaderViewHolder(binding)
            }

            ViewState.FOOTER -> {
                val binding = parent.inflateBinding(TrackFooterViewBinding::inflate)
                TrackSearchFooterViewHolder(binding, onClick)
            }
        }
    }

    override fun onBindViewHolder(holder: TrackSearchViewHolder, position: Int) {}

    override fun getItemCount(): Int = 1
}
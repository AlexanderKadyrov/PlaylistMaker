package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import com.practicum.playlistmaker.databinding.TrackFooterViewBinding

class TrackSearchFooterViewHolder(
    binding: TrackFooterViewBinding,
    private val onClick: (() -> Unit)?
) : TrackSearchViewHolder(binding.root) {

    init {
        binding.clearHistoryButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
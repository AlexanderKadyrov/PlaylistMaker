package com.practicum.playlistmaker.features.search.presentation.adapters.holders

import com.practicum.playlistmaker.databinding.TrackErrorViewBinding

class TrackSearchErrorViewHolder(
    binding: TrackErrorViewBinding,
    private val onClick: (() -> Unit)?
) : TrackSearchViewHolder(binding.root) {

    init {
        binding.refreshButton.setOnClickListener {
            onClick?.invoke()
        }
    }
}
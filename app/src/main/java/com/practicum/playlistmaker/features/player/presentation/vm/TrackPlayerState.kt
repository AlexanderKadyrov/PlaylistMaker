package com.practicum.playlistmaker.features.player.presentation.vm

import com.practicum.playlistmaker.core.domain.model.Track

data class TrackPlayerState(
    val track: Track,
    val isPlayButtonEnabled: Boolean = false,
    val isPlaying: Boolean = false,
    val currentPosition: String? = null
)
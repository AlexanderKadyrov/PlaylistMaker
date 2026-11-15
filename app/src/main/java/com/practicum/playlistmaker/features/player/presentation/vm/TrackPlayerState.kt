package com.practicum.playlistmaker.features.player.presentation.vm

data class TrackPlayerState(
    val isPlayButtonEnabled: Boolean = false,
    val isPlaying: Boolean = false,
    val currentPosition: String? = null
)
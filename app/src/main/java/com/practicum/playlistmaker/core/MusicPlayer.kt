package com.practicum.playlistmaker.core

import android.media.MediaPlayer

class MusicPlayer(
    private val onChangeState: ((State) -> Unit)
) {

    enum class State {
        DEFAULT,
        PREPARED,
        PLAYING,
        PAUSED,
        END
    }

    private var state = State.DEFAULT

    private var player = MediaPlayer()

    fun prepare(url: String) {
        player.setDataSource(url)
        player.prepareAsync()
        player.setOnPreparedListener {
            changeState(State.PREPARED)
        }
        player.setOnCompletionListener {
            changeState(State.END)
        }
    }

    fun playbackControl() {
        when(state) {
            State.PLAYING -> {
                pause()
            }
            State.PREPARED, State.PAUSED, State.END -> {
                start()
            }
            else -> {}
        }
    }

    private fun start() {
        player.start()
        changeState(State.PLAYING)
    }

    private fun pause() {
        player.pause()
        changeState(State.PAUSED)
    }

    private fun changeState(state: State) {
        this.state = state
        onChangeState(state)
    }
}
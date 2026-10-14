package com.practicum.playlistmaker.core

import android.media.MediaPlayer

import android.os.Handler
import android.os.Looper

class MusicPlayer(
    private val onChangeState: ((State) -> Unit),
    private val onChangeCurrentPosition: ((Int) -> Unit)
) {

    enum class State {
        DEFAULT,
        PREPARED,
        PLAYING,
        PAUSED,
        END
    }

    private val handler = Handler(Looper.getMainLooper())
    private val timerTask = object : Runnable {
        override fun run() {
            onChangeCurrentPosition(player.currentPosition)
            handler.postDelayed(this, TIMER_DELAY)
        }
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

    fun onPause() {
        pause()
    }

    fun onDestroy() {
        stop()
        player.release()
    }

    private fun start() {
        player.start()
        changeState(State.PLAYING)
    }

    private fun pause() {
        player.pause()
        changeState(State.PAUSED)
    }

    private fun stop() {
        player.stop()
        changeState(State.END)
    }

    private fun changeState(state: State) {
        when (state) {
            State.PLAYING -> {
                handler.post(timerTask)
            } else -> {
                handler.removeCallbacks(timerTask)
            }
        }
        this.state = state
        onChangeState(state)
    }

    companion object {
        private const val TIMER_DELAY = 500L
    }
}
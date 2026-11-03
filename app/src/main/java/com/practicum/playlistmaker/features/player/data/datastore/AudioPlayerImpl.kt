package com.practicum.playlistmaker.features.player.data.datastore

import android.media.MediaPlayer
import android.os.Handler
import android.os.Looper
import com.practicum.playlistmaker.features.player.domain.AudioPlayer
import com.practicum.playlistmaker.features.player.domain.AudioPlayerState

class AudioPlayerImpl : AudioPlayer {

    private var onChangeState: ((AudioPlayerState) -> Unit)? = null
    private var onChangeCurrentPosition: ((Int) -> Unit)? = null

    private val handler = Handler(Looper.getMainLooper())
    private val timerTask = object : Runnable {
        override fun run() {
            onChangeCurrentPosition?.invoke(player.currentPosition)
            handler.postDelayed(this, TIMER_DELAY)
        }
    }

    private var state = AudioPlayerState.DEFAULT

    private var player = MediaPlayer()

    override fun configure(
        url: String,
        onChangeState: ((AudioPlayerState) -> Unit),
        onChangeCurrentPosition: ((Int) -> Unit)
    ) {
        this.onChangeState = onChangeState
        this.onChangeCurrentPosition = onChangeCurrentPosition
        player.setDataSource(url)
        player.prepareAsync()
        player.setOnPreparedListener {
            changeState(AudioPlayerState.PREPARED)
        }
        player.setOnCompletionListener {
            player.seekTo(0)
            changeState(AudioPlayerState.END)
        }
    }

    override fun playbackControl() {
        when (state) {
            AudioPlayerState.PLAYING -> {
                pause()
            }

            AudioPlayerState.PREPARED, AudioPlayerState.PAUSED, AudioPlayerState.END -> {
                start()
            }

            else -> {}
        }
    }

    override fun onPause() {
        pause()
    }

    override fun onDestroy() {
        stop()
        player.release()
    }

    private fun start() {
        player.start()
        changeState(AudioPlayerState.PLAYING)
    }

    private fun pause() {
        player.pause()
        changeState(AudioPlayerState.PAUSED)
    }

    private fun stop() {
        player.stop()
        changeState(AudioPlayerState.END)
    }

    private fun changeState(state: AudioPlayerState) {
        when (state) {
            AudioPlayerState.PLAYING -> {
                handler.post(timerTask)
            }

            else -> {
                handler.removeCallbacks(timerTask)
            }
        }
        this.state = state
        onChangeState?.invoke(state)
    }

    companion object Companion {
        private const val TIMER_DELAY = 500L
    }
}
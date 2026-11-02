package com.practicum.playlistmaker

import android.app.Application
import com.practicum.playlistmaker.di.Creator

class App : Application() {

    val creator: Creator by lazy {
        Creator(this)
    }
}
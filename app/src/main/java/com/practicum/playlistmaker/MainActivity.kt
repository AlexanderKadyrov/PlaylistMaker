package com.practicum.playlistmaker

import androidx.appcompat.app.AppCompatActivity

import android.widget.FrameLayout
import android.content.Intent
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setOnClickSearch()
        setOnClickMedia()
        setOnClickSettings()
    }

    private fun setOnClickSearch() {
        val searchFrameLayout = findViewById<FrameLayout>(R.id.search)
        searchFrameLayout.setOnClickListener {
            startActivity<SearchActivity>()
        }
    }

    private fun setOnClickMedia() {
        val mediaFrameLayout = findViewById<FrameLayout>(R.id.media)
        mediaFrameLayout.setOnClickListener {
            startActivity<MediaActivity>()
        }
    }

    private fun setOnClickSettings() {
        val settingsOnClickListener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity<SettingsActivity>()
            }
        }
        val settingsFrameLayout = findViewById<FrameLayout>(R.id.settings)
        settingsFrameLayout.setOnClickListener(settingsOnClickListener)
    }

    private inline fun <reified T: Any>AppCompatActivity.startActivity() {
        val intent = Intent(this, T::class.java)
        startActivity(intent)
    }
}
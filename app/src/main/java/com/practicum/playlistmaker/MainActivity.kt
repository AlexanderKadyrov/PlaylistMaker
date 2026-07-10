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

        listOf(R.id.search, R.id.media)
            .forEach { elementId ->
                val element = findViewById<FrameLayout>(elementId)
                element.setOnClickListener {
                    when (elementId) {
                        R.id.search -> {
                            startActivity<SearchActivity>()
                        }
                        R.id.media -> {
                            startActivity<MediaActivity>()
                        }
                    }
                }
            }

        val settingsOnClickListener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                startActivity<SettingsActivity>()
            }
        }
        val settingsFrameLayout = findViewById<FrameLayout>(R.id.settings)
        settingsFrameLayout.setOnClickListener(settingsOnClickListener)
    }

    private inline fun <reified T: Any>startActivity() {
        val intent = Intent(this@MainActivity, T::class.java)
        startActivity(intent)
    }
}
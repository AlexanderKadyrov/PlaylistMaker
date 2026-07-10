package com.practicum.playlistmaker

import androidx.appcompat.app.AppCompatActivity

import android.content.Intent
import android.widget.FrameLayout
import android.widget.Toast
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
                    showToast("On click elementId: $elementId")
                }
            }

        val settingsOnClickListener: View.OnClickListener = object : View.OnClickListener {
            override fun onClick(v: View?) {
                val displayIntent = Intent(this@MainActivity, SettingsActivity::class.java)
                startActivity(displayIntent)
            }
        }
        val settingsFrameLayout = findViewById<FrameLayout>(R.id.settings)
        settingsFrameLayout.setOnClickListener(settingsOnClickListener)
    }

    private fun showToast(text: String) {
        Toast.makeText(this@MainActivity, text, Toast.LENGTH_SHORT)
            .show()
    }
}
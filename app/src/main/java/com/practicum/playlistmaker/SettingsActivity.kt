package com.practicum.playlistmaker

import com.practicum.playlistmaker.extensions.configureToolbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import android.widget.FrameLayout
import android.widget.Switch

import com.practicum.playlistmaker.extensions.intentActionSendTo
import com.practicum.playlistmaker.extensions.intentActionSend
import com.practicum.playlistmaker.extensions.intentActionView

import com.practicum.playlistmaker.repositories.ThemeRepository

class SettingsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        configureToolbar()
        setOnClickDarkTheme()
        setOnClickShareTheApp()
        setOnClickWriteToSupport()
        setOnClickUserAgreement()
    }

    private fun setOnClickDarkTheme() {
        val settingsSwitch = findViewById<Switch>(R.id.settings_switch)
        settingsSwitch.isChecked = ThemeRepository.isDarkTheme()
        settingsSwitch.setOnCheckedChangeListener { _, isChecked ->
            ThemeRepository.switchTheme(isChecked)
        }
    }

    private fun setOnClickShareTheApp() {
        val shareTheApp = findViewById<FrameLayout>(R.id.share_the_app)
        shareTheApp.setOnClickListener {
            val intent = intentActionSend(
                text = getString(R.string.share_the_app_url)
            )
            startActivity(intent)
        }
    }

    private fun setOnClickWriteToSupport() {
        val writeToSupport = findViewById<FrameLayout>(R.id.write_to_support)
        writeToSupport.setOnClickListener {
            val intent = intentActionSendTo(
                email = getString(R.string.write_to_support_extra_email),
                subject = getString(R.string.write_to_support_extra_subject),
                text = getString(R.string.write_to_support_extra_text)
            )
            startActivity(intent)
        }
    }

    private fun setOnClickUserAgreement() {
        val userAgreement = findViewById<FrameLayout>(R.id.user_agreement)
        userAgreement.setOnClickListener {
            val intent = intentActionView(
                uri = getString(R.string.user_agreement_url)
            )
            startActivity(intent)
        }
    }
}
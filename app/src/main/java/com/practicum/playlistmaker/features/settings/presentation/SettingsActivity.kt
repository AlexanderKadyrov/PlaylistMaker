package com.practicum.playlistmaker.features.settings.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practicum.playlistmaker.App
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.intentActionSend
import com.practicum.playlistmaker.core.extensions.intentActionSendTo
import com.practicum.playlistmaker.core.extensions.intentActionView
import com.practicum.playlistmaker.databinding.ActivitySettingsBinding
import com.practicum.playlistmaker.features.settings.di.settingsInteractor

class SettingsActivity : AppCompatActivity() {

    private val settingsInteractor by lazy {
        (applicationContext as App).creator.settingsInteractor()
    }

    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        configureUI()
    }

    private fun configureUI() = with(binding) {
        settingsSwitch.isChecked = settingsInteractor.isDarkTheme()
        settingsSwitch.setOnCheckedChangeListener { _, isChecked ->
            settingsInteractor.switchTheme(isChecked)
        }

        shareTheApp.setOnClickListener {
            val intent = intentActionSend(
                text = getString(R.string.share_the_app_url)
            )
            startActivity(intent)
        }

        writeToSupport.setOnClickListener {
            val intent = intentActionSendTo(
                email = getString(R.string.write_to_support_extra_email),
                subject = getString(R.string.write_to_support_extra_subject),
                text = getString(R.string.write_to_support_extra_text)
            )
            startActivity(intent)
        }

        userAgreement.setOnClickListener {
            val intent = intentActionView(
                uri = getString(R.string.user_agreement_url)
            )
            startActivity(intent)
        }
    }
}
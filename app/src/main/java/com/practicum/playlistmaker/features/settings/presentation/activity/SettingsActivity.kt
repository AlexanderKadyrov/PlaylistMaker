package com.practicum.playlistmaker.features.settings.presentation.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.practicum.playlistmaker.R
import com.practicum.playlistmaker.core.extensions.configureToolbar
import com.practicum.playlistmaker.core.extensions.intentActionSend
import com.practicum.playlistmaker.core.extensions.intentActionSendTo
import com.practicum.playlistmaker.core.extensions.intentActionView
import com.practicum.playlistmaker.core.extensions.viewModelFactory
import com.practicum.playlistmaker.databinding.ActivitySettingsBinding
import com.practicum.playlistmaker.di.creator
import com.practicum.playlistmaker.features.settings.di.settingsViewModel

class SettingsActivity : AppCompatActivity() {

    private val viewModel by viewModelFactory { creator.settingsViewModel() }

    private val binding by lazy { ActivitySettingsBinding.inflate(layoutInflater) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        configureToolbar()
        configureUI()
        observeViewModel()
    }

    private fun configureUI() = with(binding) {
        settingsSwitch.setOnCheckedChangeListener { _, isChecked ->
            viewModel.switchTheme(isChecked)
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

    private fun observeViewModel() {
        viewModel.state.observe(this) { isDarkTheme ->
            render(isDarkTheme)
        }
    }

    private fun render(isDarkTheme: Boolean) = with(binding) {
        settingsSwitch.isChecked = isDarkTheme
    }
}
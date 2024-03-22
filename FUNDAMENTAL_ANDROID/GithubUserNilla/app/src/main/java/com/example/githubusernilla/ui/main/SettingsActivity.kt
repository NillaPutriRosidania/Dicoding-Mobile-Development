package com.example.githubusernilla.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.githubusernilla.R
import com.google.android.material.switchmaterial.SwitchMaterial
import androidx.lifecycle.observe


class SettingsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        val switchTheme = findViewById<SwitchMaterial>(R.id.switch_theme)
        val pref = SettingPreferences.getInstance(application.dataStore)
        val settingViewModel = ViewModelProvider(this, SettingViewModelFactory(pref)).get(SettingViewModel::class.java)

        switchTheme.isChecked = settingViewModel.themeSetting.value

        lifecycleScope.launchWhenStarted {
            settingViewModel.themeSetting.collect { isDarkModeActive ->
                switchTheme.isChecked = isDarkModeActive
            }
        }

        switchTheme.setOnCheckedChangeListener { _, isChecked ->
            settingViewModel.saveThemeSetting(isChecked)
        }
    }
}
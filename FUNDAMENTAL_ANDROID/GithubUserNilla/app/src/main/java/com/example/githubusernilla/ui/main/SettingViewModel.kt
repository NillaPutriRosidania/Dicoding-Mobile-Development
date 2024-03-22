package com.example.githubusernilla.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SettingViewModel(private val pref: SettingPreferences) : ViewModel() {

    private val _themeSetting = MutableStateFlow(false)
    val themeSetting: StateFlow<Boolean> get() = _themeSetting

    init {
        viewModelScope.launch {
            pref.getThemeSetting().collect { theme ->
                _themeSetting.value = theme
            }
        }
    }

    fun saveThemeSetting(isDarkModeActive: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDarkModeActive)
        }
    }
}

package com.example.githubusernilla.ui.main

import com.example.githubusernilla.R
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.lifecycle.lifecycleScope

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var progressBar: ProgressBar
    private lateinit var progressText: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val pref = SettingPreferences.getInstance(applicationContext.dataStore)

        lifecycleScope.launchWhenStarted {
            pref.getThemeSetting().collect { isDarkModeActive ->
                if (isDarkModeActive) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                }
            }
        }

        setContentView(R.layout.activity_splash_screen)

        progressBar = findViewById(R.id.progressBar)
        progressText = findViewById(R.id.progressText)
        val appName = "Github App"
        progressText.text = appName

        var progress = 0

        val handler = Handler(Looper.getMainLooper())
        val runnable = object : Runnable {
            override fun run() {
                if (progress <= 100) {
                    progressBar.progress = progress
                    progressText.text = "$appName $progress%"
                    progress++
                    handler.postDelayed(this, 100)
                } else {
                    startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
                    finish()
                }
            }
        }
        handler.postDelayed(runnable, 100)
    }
}

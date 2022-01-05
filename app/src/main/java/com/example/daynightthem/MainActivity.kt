package com.example.daynightthem

import android.content.Context
import android.content.Intent
import android.content.res.Configuration
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import com.example.daynightthem.databinding.ActivityMainBinding

const val PREFS_NAME = "theme_prefs"
const val KEY_THEME = "prefs.theme"
const val THEME_UNDEFINED = -1
const val THEME_LIGHT = 0
const val THEME_DARK = 1
const val THEME_SYSTEM = 2
const val THEME_BATTERY = 3
class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    private val sharedPrefs by lazy {  getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initThemeListener()
        initTheme()


    }


    private fun initThemeListener(){
        binding.themeGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.themeLight -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_NO, THEME_LIGHT)
                    binding.titleTheme.text = "ThemeLight"
                }
                R.id.themeDark -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_YES, THEME_DARK)
                    binding.titleTheme.text = "ThemeDark"
                }
                R.id.themeBattery -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY, THEME_BATTERY)
                    binding.titleTheme.text = "ThemeBattery"

                }
                R.id.themeSystem -> {
                    setTheme(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM, THEME_SYSTEM)
                    binding.titleTheme.text = "ThemeSystem"

                }
            }
        }
    }

    private fun setTheme(themeMode: Int, prefsMode: Int) {
        AppCompatDelegate.setDefaultNightMode(themeMode)
        saveTheme(prefsMode)
//       recreate()
    }

    private fun initTheme() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
            binding.themeSystem.visibility = View.VISIBLE
        } else {
            binding.themeSystem.visibility = View.GONE
        }
        when (getSavedTheme()) {
            THEME_LIGHT ->  binding.themeLight.isChecked = true
            THEME_DARK ->  binding.themeDark.isChecked = true
            THEME_SYSTEM ->  binding.themeSystem.isChecked = true
            THEME_BATTERY ->  binding.themeBattery.isChecked = true
            THEME_UNDEFINED -> {
                when (resources.configuration.uiMode.and(Configuration.UI_MODE_NIGHT_MASK)) {
                    Configuration.UI_MODE_NIGHT_NO ->  binding.themeLight.isChecked = true
                    Configuration.UI_MODE_NIGHT_YES ->  binding.themeDark.isChecked = true
                    Configuration.UI_MODE_NIGHT_UNDEFINED ->  binding.themeLight.isChecked = true
                }
            }
        }
    }

    private fun saveTheme(theme: Int) = sharedPrefs.edit().putInt(KEY_THEME, theme).apply()

    private fun getSavedTheme() = sharedPrefs.getInt(KEY_THEME, THEME_UNDEFINED)
}
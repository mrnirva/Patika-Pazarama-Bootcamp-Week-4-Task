package com.arcapp.weatherapp.ui.activity

import android.app.Application
import android.content.Context
import androidx.appcompat.app.AppCompatDelegate
import com.arcapp.weatherapp.constant.Constants
import com.arcapp.weatherapp.data.preference.SharedPref
import com.arcapp.weatherapp.enum.AppTheme

// Base Application
class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        val sharedPref = SharedPref(applicationContext)

        // Activate the selected theme
        when(sharedPref.getAppTheme()){

            AppTheme.System.toString() -> {   // Selection of the System
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            }

            AppTheme.Light.toString() -> {  // Selection of the Light
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }

            AppTheme.Dark.toString() -> { // Selection of the Dark
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

            // Default
            else -> AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

        }

    }

}
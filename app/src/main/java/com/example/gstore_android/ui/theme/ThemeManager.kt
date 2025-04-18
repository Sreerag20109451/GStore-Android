package com.example.gstore_android.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext

class ThemeManager {

    var isDarkTheme  = mutableStateOf(false)
        private set

    fun toggleTheme() {
        isDarkTheme.value = !isDarkTheme.value
    }

    fun setSystemTheme(isSystemDark: Boolean) {
        isDarkTheme.value = isSystemDark
    }
}

@Composable
fun rememberThemeManager(): ThemeManager {
    val context = LocalContext.current
    val systemDarkTheme = isSystemInDarkTheme()
    val themeManager = remember { ThemeManager() }

    // Update when system theme changes
    LaunchedEffect (systemDarkTheme) {
        themeManager.setSystemTheme(systemDarkTheme)
    }

    return themeManager
}
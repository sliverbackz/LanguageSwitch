package com.example.languageswtichapp

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

const val LANGUAGE_ENGLISH = "en"
const val LANGUAGE_MM_UNICODE = "uni"
const val LANGUAGE_MM_ZAWGYI = "zaw"

class AppPreference(context: Context) {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)

    companion object {
        private const val LANGUAGE = "language"
    }

    fun saveLanguage(language: String) {
        pref.edit {
            putString(LANGUAGE, language)
        }
    }

    fun getLanguage() = pref.getString(LANGUAGE, LANGUAGE_ENGLISH)


}
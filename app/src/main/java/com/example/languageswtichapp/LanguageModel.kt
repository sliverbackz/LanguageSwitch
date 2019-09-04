package com.example.languageswtichapp

import android.annotation.TargetApi
import android.content.Context
import android.os.Build
import androidx.core.content.edit
import androidx.preference.PreferenceManager
import java.util.*

class LanguageModel(val context: Context) {
    private val pref = PreferenceManager.getDefaultSharedPreferences(context)


    companion object {
        private const val LANGUAGE = "language"
        const val LANGUAGE_ENGLISH = "en"
        const val LANGUAGE_MM_UNICODE = "my"
        const val LANGUAGE_MM_ZAWGYI = "mua-rCM"
    }


    fun getLanguage(): String {
        return getPersistedData(Locale.getDefault().language)
    }

    fun setLocale(language: String): Context {
        persist(language)
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            updateResources(language)
        } else updateResourcesLegacy(language)
    }

    private fun getPersistedData(defaultLanguage: String): String {
        return pref.getString(LANGUAGE, defaultLanguage)!!
    }

    fun persist(language: String) {
        pref.edit {
            putString(LANGUAGE, language)
        }
    }

    @TargetApi(Build.VERSION_CODES.N)
    fun updateResources(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val configuration = context.resources.configuration
        configuration.setLocale(locale)
        return context.createConfigurationContext(configuration)
    }

    fun updateResourcesLegacy(language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)
        val resources = context.resources
        val configuration = resources.configuration
        configuration.locale = locale
        resources.updateConfiguration(configuration, resources.displayMetrics)
        return context
    }

}
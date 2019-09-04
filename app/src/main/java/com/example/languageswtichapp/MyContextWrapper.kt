package com.example.languageswtichapp

import android.annotation.TargetApi
import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.os.Build
import java.util.Locale

class MyContextWrapper(base: Context) : ContextWrapper(base) {
    companion object {

        fun wrap(context: Context, language: String): ContextWrapper {
            var context = context
            val config = context.resources.configuration
            var sysLocale: Locale? = null
            sysLocale = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                getSystemLocale(config)
            } else {
                getSystemLocaleLegacy(config)
            }
            if (language != "" && sysLocale.language != language) {
                val locale = Locale(language)
                Locale.setDefault(locale)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    setSystemLocale(config, locale)
                } else {
                    setSystemLocaleLegacy(config, locale)
                }

            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
                context = context.createConfigurationContext(config)
            } else {
                context.resources.updateConfiguration(config, context.resources.displayMetrics)
            }
            return MyContextWrapper(context)
        }

        @Deprecated("Deprecation")
        fun getSystemLocaleLegacy(config: Configuration): Locale {
            return config.locale
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun getSystemLocale(config: Configuration): Locale {
            return config.locales.get(0)
        }

        @Deprecated("Deprecation")
        fun setSystemLocaleLegacy(config: Configuration, locale: Locale) {
            config.locale = locale
        }

        @TargetApi(Build.VERSION_CODES.N)
        fun setSystemLocale(config: Configuration, locale: Locale) {
            config.setLocale(locale)
        }
    }
}

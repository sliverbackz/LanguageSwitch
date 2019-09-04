package com.example.languageswtichapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    lateinit var preference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        btn_eng.setOnClickListener {
            preference.saveLanguage(LANGUAGE_ENGLISH)
            doRestart()
        }

        btn_uni.setOnClickListener {
            preference.saveLanguage(LANGUAGE_MM_UNICODE)
            doRestart()
        }

        btn_zaw.setOnClickListener {
            preference.saveLanguage(LANGUAGE_MM_ZAWGYI)
            doRestart()
        }

        btn_next.setOnClickListener {
            startActivity(Intent(this, NextActivity::class.java))
        }
    }

    fun doRestart() {
        finish()
        startActivity(intent)
    }

    override fun attachBaseContext(newBase: Context?) {
        preference = AppPreference(newBase!!)
        super.attachBaseContext(MyContextWrapper.wrap(newBase!!, preference.getLanguage()!!))
    }
}

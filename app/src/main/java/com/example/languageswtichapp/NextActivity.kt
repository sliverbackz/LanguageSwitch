package com.example.languageswtichapp

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class NextActivity : AppCompatActivity() {
    private lateinit var preference: AppPreference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_next)
    }

    override fun attachBaseContext(newBase: Context?) {
        preference = AppPreference(newBase!!)
        super.attachBaseContext(MyContextWrapper.wrap(newBase, preference.getLanguage()!!))
    }
}

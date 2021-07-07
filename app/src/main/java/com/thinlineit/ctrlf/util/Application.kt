package com.thinlineit.ctrlf.util

import android.app.Application

class Application : Application() {

    companion object {
        lateinit var prefs: PreferenceUtil
    }

    override fun onCreate() {
        prefs = PreferenceUtil(applicationContext)
        super.onCreate()
    }
}
package com.bsktech.codingassignmentjet2travel

import android.app.Application
import android.content.Context

class MyApplication() : Application() {

    override fun onCreate() {
        super.onCreate()
        MyApplication.appContext = applicationContext
    }

    companion object {
        lateinit var appContext: Context
    }

}
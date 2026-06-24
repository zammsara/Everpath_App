package com.everpath

import android.app.Application
import com.everpath.di.AppContainer

class EverpathApplication : Application() {

    lateinit var appContainer: AppContainer
        private set

    override fun onCreate() {
        super.onCreate()

        appContainer = AppContainer(this)
    }
}
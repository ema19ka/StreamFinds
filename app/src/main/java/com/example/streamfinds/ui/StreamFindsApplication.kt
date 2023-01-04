package com.example.streamfinds.ui

import android.app.Application
import com.example.streamfinds.data.AppContainer
import com.example.streamfinds.data.DefaultAppContainer

class StreamFindsApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer()
    }
}
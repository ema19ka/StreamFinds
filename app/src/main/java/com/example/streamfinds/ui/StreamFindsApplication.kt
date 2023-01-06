package com.example.streamfinds.ui

import android.app.Application
import com.example.streamfinds.data.AppContainer

class StreamFindsApplication : Application() {
    /** AppContainer instance used by the rest of classes to obtain dependencies */
    //lateinit var container: AppContainer
    override fun onCreate() {
        super.onCreate()
        //container = DefaultAppContainer()
    }
}
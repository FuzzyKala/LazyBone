package com.example.lazybone.main

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class LazyBoneApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            // Android context
            androidContext(this@LazyBoneApplication)
            // Load modules
            modules(appModule)
        }
    }
}

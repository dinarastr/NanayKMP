package com.dinarastepina.nanaykmp

import android.app.Application
import com.dinarastepina.nanaykmp.di.platformModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class DictionaryApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger(Level.DEBUG) // Optional: Set Koin logger level
            androidContext(this@DictionaryApplication) // Provide the Android context
            modules(platformModule()) // Load your platform-specific module
        }
    }
}
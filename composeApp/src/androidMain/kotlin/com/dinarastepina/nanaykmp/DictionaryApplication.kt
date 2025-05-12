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
            androidLogger(Level.DEBUG)
            androidContext(this@DictionaryApplication)
            modules(platformModule())
        }
    }
}
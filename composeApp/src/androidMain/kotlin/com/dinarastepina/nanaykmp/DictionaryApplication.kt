package com.dinarastepina.nanaykmp

import android.app.Application
import com.dinarastepina.nanaykmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class DictionaryApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@DictionaryApplication)
        }
    }
} 
package com.dinarastr.nanay

import android.app.Application
import com.dinarastr.nanay.di.initKoin
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